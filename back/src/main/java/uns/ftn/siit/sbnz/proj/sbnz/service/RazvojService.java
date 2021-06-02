package uns.ftn.siit.sbnz.proj.sbnz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uns.ftn.siit.sbnz.proj.sbnz.dto.RazvojRequest;
import uns.ftn.siit.sbnz.proj.sbnz.dto.RazvojResponse;
import uns.ftn.siit.sbnz.proj.sbnz.model.Razvoj;
import uns.ftn.siit.sbnz.proj.sbnz.repository.RazvojRepository;

import java.util.List;

@Service
public class RazvojService {

    private final RazvojRepository razvojRepository;

    @Autowired
    public RazvojService(RazvojRepository razvojRepository) {
        this.razvojRepository = razvojRepository;
    }

    public List<Razvoj> getAll(){
        return razvojRepository.findAll();
    }

    public Long addOne(Razvoj r){
        r = razvojRepository.save(r);
        return r.getId();
    }


    public List<Razvoj> getAll(Long userid, Razvoj.StanjeRazvoja status) {
        return razvojRepository.getAllByVlasnikIdAndStanjeRazvoja(userid, status);
    }
}
