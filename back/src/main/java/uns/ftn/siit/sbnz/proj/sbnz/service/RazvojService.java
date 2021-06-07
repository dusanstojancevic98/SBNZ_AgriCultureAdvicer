package uns.ftn.siit.sbnz.proj.sbnz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import uns.ftn.siit.sbnz.proj.sbnz.dto.RazvojResponse;
import uns.ftn.siit.sbnz.proj.sbnz.exceptions.BadRequestException;
import uns.ftn.siit.sbnz.proj.sbnz.mappers.RazvojMapper;
import uns.ftn.siit.sbnz.proj.sbnz.model.Akcija;
import uns.ftn.siit.sbnz.proj.sbnz.model.Korisnik;
import uns.ftn.siit.sbnz.proj.sbnz.model.Razvoj;
import uns.ftn.siit.sbnz.proj.sbnz.repository.AkcijaRepository;
import uns.ftn.siit.sbnz.proj.sbnz.repository.RazvojRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RazvojService {

    private final RazvojRepository razvojRepository;
    private final AkcijaRepository akcijaRepository;
    private OdabirUsevaService odabirUsevaService;
    private final RazvojMapper razvojMapper;


    @Autowired
    public RazvojService(RazvojRepository razvojRepository, AkcijaRepository akcijaRepository, OdabirUsevaService odabirUsevaService, RazvojMapper razvojMapper) {
        this.razvojRepository = razvojRepository;
        this.akcijaRepository = akcijaRepository;
        this.odabirUsevaService = odabirUsevaService;
        this.razvojMapper = razvojMapper;
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
        }
    }

    public void odaberiUsev(Long id, Long usevId) throws Exception {

        Razvoj razvoj = razvojRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        if(razvoj.getStanjeRazvoja()!= Razvoj.StanjeRazvoja.INICIJALNO && razvoj.getStanjeRazvoja()!= Razvoj.StanjeRazvoja.SPREMNO  ){
            throw new BadRequestException("Razvoj nije u dobrom stanju vec u stanju "+razvoj.getStanjeRazvoja().toString());
        }
        razvoj.odaberiUsev(usevId);
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
        Akcija akcija = akcijaRepository.getAkcijaByIdAndRazvojId(id, razvojId).orElseThrow(()->new BadRequestException("Ne postoji akcija za taj razvoj"));
        akcija.setStanjeAkcije(stanjeAkcije);
        razvoj.setTrenutnaAkcija(razvoj.getTrenutnaAkcija().stream().filter(a->!a.getId().equals(id)).collect(Collectors.toList()));
        razvoj.getIstorijaAkcija().add(akcija);
        razvojRepository.save(razvoj);
    }


    public void obrisiRazvoj(Long id){
        razvojRepository.deleteById(id);
    }


}
