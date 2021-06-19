package uns.ftn.siit.sbnz.proj.sbnz.service;

import org.kie.api.runtime.KieSession;
import org.kie.api.time.SessionPseudoClock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import uns.ftn.siit.sbnz.proj.sbnz.dto.RazvojResponse;
import uns.ftn.siit.sbnz.proj.sbnz.exceptions.BadRequestException;
import uns.ftn.siit.sbnz.proj.sbnz.exceptions.FatalErrorException;
import uns.ftn.siit.sbnz.proj.sbnz.mappers.RazvojMapper;
import uns.ftn.siit.sbnz.proj.sbnz.model.*;
import uns.ftn.siit.sbnz.proj.sbnz.model.enums.TipPadavine;
import uns.ftn.siit.sbnz.proj.sbnz.repository.AkcijaRepository;
import uns.ftn.siit.sbnz.proj.sbnz.repository.RazvojRepository;
import uns.ftn.siit.sbnz.proj.sbnz.repository.UsevPodaciRepository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class RazvojService {

    private final RazvojRepository razvojRepository;
    private final AkcijaRepository akcijaRepository;
    private OdabirUsevaService odabirUsevaService;

    private UsevPodaciRepository usevPodaciRepository;
    private final RazvojMapper razvojMapper;

    private ApplicationContext applicationContext;

    public void addStanjeZemljista(Long rid, StanjeZemljista stanjeZemljista) {
        Razvoj r = razvojRepository.findById(rid).orElseThrow(()->new ResourceNotFoundException("Ne postoji razvoj sa tim id-jem: "+rid));
        r.addStanjeZemljista(stanjeZemljista);
        razvojRepository.save(r);
    }

    class VPThread extends Thread{
        KieSession session;
        public VPThread(KieSession kieSession) {
            this.session = kieSession;
        }

        @Override
        public void run() {
            super.run();
            generisiVremenskuPrognozu();
        }

        private void generisiVremenskuPrognozu() {
            Random rng = new Random();
            SessionPseudoClock clock = session.getSessionClock();
            session.getSessionClock();
            LocalDateTime pocetak = LocalDateTime.now();
            while(true){
                VremenskaPrognoza vp = new VremenskaPrognoza();
                vp.setTipPadavine(getTipPadavine(rng));
                System.out.println(vp.getTipPadavine());
                vp.setMaximalnaTemperatura(20 + rng.nextInt(15));
                vp.setMaximalnaTemperatura(-10 + rng.nextInt(20));
                vp.setJacinaVetra(4 + rng.nextInt(5));
                vp.setKolicinaPadavina(2 + rng.nextInt(4));
                session.insert(vp);
                vp.setDatum(pocetak);

                pocetak = pocetak.plusDays(1);
                clock.advanceTime(1, TimeUnit.DAYS);
                try {
                    Thread.sleep(2000);
//                    sleep(5000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        private TipPadavine getTipPadavine(Random rng){
//            return TipPadavine.SNEG;
            double r = rng.nextDouble();
            if(r<0.7){
                return TipPadavine.KISA;
            }
            if(r<0.8){
                return TipPadavine.GRAD;
            }
            return TipPadavine.SNEG;
        }
    }

    class AkcijeThread extends Thread{

        private final KieSession kieSession;
        private Razvoj razvoj;
        private RazvojService razvojService;

        public AkcijeThread(KieSession kieSession, Razvoj razvoj, RazvojService razvojService) {
            this.kieSession = kieSession;
            this.razvoj = razvoj;
            this.razvojService = razvojService;
        }


        @Override
        public void run() {
            super.run();
            kieSession.setGlobal("razvojService", razvojService);
            kieSession.insert(razvoj);
            kieSession.insert(razvoj.getKonfiguracija().getZemljiste());
            kieSession.insert(razvoj.getKonfiguracija().getVremenskiPodaci());
            System.out.println("STARTED");
//            kieSession.fireAllRules();
            kieSession.fireUntilHalt();
            System.out.println("----------HALTED----------");
        }


        public void speedUp(Integer days) {
            SessionPseudoClock clock = kieSession.getSessionClock();
            System.out.println(clock.advanceTime(days, TimeUnit.HOURS));
        }
    }
    private Map<Long,AkcijeThread> akcijeThreads = new HashMap<>();

    @Autowired
    public RazvojService(RazvojRepository razvojRepository, AkcijaRepository akcijaRepository, OdabirUsevaService odabirUsevaService, UsevPodaciRepository usevPodaciRepository, RazvojMapper razvojMapper, ApplicationContext applicationContext) {
        this.razvojRepository = razvojRepository;
        this.akcijaRepository = akcijaRepository;
        this.odabirUsevaService = odabirUsevaService;
        this.usevPodaciRepository = usevPodaciRepository;
        this.razvojMapper = razvojMapper;
        this.applicationContext = applicationContext;

        pokreniRazvoje();
    }

    public List<Razvoj> getAll(){
        return razvojRepository.findAll();
    }

    public Long addOne(Razvoj r, Korisnik vlasnik){
        r.setVlasnik(vlasnik);
        r = odabirUsevaService.preporuciUseve(r);
        r = razvojRepository.save(r);
        return r.getId();
    }



    public List<RazvojResponse> getAll(Long userid, Razvoj.StanjeRazvoja status) {
        return razvojRepository.getAllByVlasnikIdAndStanjeRazvoja(userid, status).stream().map(razvojMapper::toResponse).collect(Collectors.toList());
    }


    public RazvojResponse getOneByUserId(Long userid, Long id){
          return razvojMapper.toResponse(razvojRepository.getByVlasnikIdAndId(userid, id));
    }

    public void pokreni(Long id) {
        Optional<Razvoj> or = razvojRepository.findById(id);
        if(or.isPresent()){
            Razvoj r = or.get();
            if(r.getStanjeRazvoja()!= Razvoj.StanjeRazvoja.SPREMNO && r.getStanjeRazvoja()!= Razvoj.StanjeRazvoja.PAUZIRANO){
                throw new BadRequestException("Razvoj nije u dobrom stanju vec u stanju "+r.getStanjeRazvoja().toString());
            }
            r.setStanjeRazvoja(Razvoj.StanjeRazvoja.U_TOKU);
            razvojRepository.save(r);
            r = razvojRepository.findById(id).orElseThrow(()->new FatalErrorException("Greska sa razvojima."));


            pokreniRazvoj(r);

        }else{
            throw new ResourceNotFoundException();
        }
    }

    private void pokreniRazvoje(){
        try{
            List<Razvoj> razvojiUToku = razvojRepository.getAllByStanjeRazvoja(Razvoj.StanjeRazvoja.U_TOKU);
            razvojiUToku.forEach(this::pokreniRazvoj);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void pokreniRazvoj(Razvoj r) {
        if(akcijeThreads.containsKey(r.getId())){
            AkcijeThread akcijaThread = akcijeThreads.get(r.getId());
            akcijaThread.start();
        }else{
            KieSession kieSession = applicationContext.getBean(KieSession.class);
            AkcijeThread akcijaThread = new AkcijeThread(kieSession, r, this);
            VPThread thread = new VPThread(kieSession);
            thread.start();

            akcijaThread.start();
            akcijeThreads.put(r.getId(), akcijaThread);
        }
    }

    public void odaberiUsev(Long id, Long usevId) throws Exception {

        Razvoj razvoj = razvojRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        if(razvoj.getStanjeRazvoja()!= Razvoj.StanjeRazvoja.INICIJALNO && razvoj.getStanjeRazvoja()!= Razvoj.StanjeRazvoja.SPREMNO  ){
            throw new BadRequestException("Razvoj nije u dobrom stanju vec u stanju "+razvoj.getStanjeRazvoja().toString());
        }
        razvoj.odaberiUsev(usevId);

        UsevPodaci odabrani =new UsevPodaci(razvoj.getOdabraniUsev());
        odabrani = usevPodaciRepository.save(odabrani);
        Konfiguracija konfiguracija = razvoj.getKonfiguracija();
        Zemljiste zemljiste = konfiguracija.getZemljiste();
        zemljiste.setTrenutanUsev(odabrani);
        konfiguracija.setZemljiste(zemljiste);
        razvoj.setKonfiguracija(konfiguracija);
        razvojRepository.save(razvoj);
    }


    public void odradiAkciju(Long id, Long razvojId){
        obradiAkciju(id, razvojId, Akcija.StanjeAkcije.OBAVLJENA);

    }
    public void odbaciAkciju(Long id, Long razvojId){
        obradiAkciju(id, razvojId, Akcija.StanjeAkcije.NEOBAVLJENA);
    }

    private void obradiAkciju(Long id, Long razvojId, Akcija.StanjeAkcije stanjeAkcije){
        Razvoj razvoj = razvojRepository.findById(razvojId).orElseThrow(ResourceNotFoundException::new);
        razvoj.getTrenutnaAkcija().stream().filter(a->a.getId().equals(id)).findFirst().orElseThrow(()->new BadRequestException("Ne postoji akcija za taj razvoj"));
        Akcija akcija = akcijaRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        akcija.setStanjeAkcije(stanjeAkcije);
        razvoj.setTrenutnaAkcija(razvoj.getTrenutnaAkcija().stream().filter(a->!a.getId().equals(id)).collect(Collectors.toList()));
        razvoj.getIstorijaAkcija().add(akcija);
        razvojRepository.save(razvoj);
    }


    public void obrisiRazvoj(Long id){
        razvojRepository.deleteById(id);
    }


    public void saveRazvoj(Razvoj r){
        razvojRepository.save(r);
    }



    public void ubrzajVreme(Long id, Integer days){
        this.akcijeThreads.get(id).speedUp(days);
    }


}
