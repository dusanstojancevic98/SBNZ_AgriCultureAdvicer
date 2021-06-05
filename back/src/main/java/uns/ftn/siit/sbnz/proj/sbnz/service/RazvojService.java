package uns.ftn.siit.sbnz.proj.sbnz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import uns.ftn.siit.sbnz.proj.sbnz.exceptions.BadRequestException;
import uns.ftn.siit.sbnz.proj.sbnz.model.Korisnik;
import uns.ftn.siit.sbnz.proj.sbnz.model.Razvoj;
import uns.ftn.siit.sbnz.proj.sbnz.repository.RazvojRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class RazvojService {

    private final RazvojRepository razvojRepository;

    private OdabirUsevaService odabirUsevaService;

    @Autowired
    public RazvojService(RazvojRepository razvojRepository, OdabirUsevaService odabirUsevaService) {
        this.razvojRepository = razvojRepository;
        this.odabirUsevaService = odabirUsevaService;
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



    public List<Razvoj> getAll(Long userid, Razvoj.StanjeRazvoja status) {
        return razvojRepository.getAllByVlasnikIdAndStanjeRazvoja(userid, status);
    }


    public Razvoj getOneByUserId(Long userid, Long id){
          return razvojRepository.getByVlasnikIdAndId(userid, id);
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

    public void obrisiRazvoj(Long id){
        razvojRepository.deleteById(id);
    }
}
