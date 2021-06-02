package uns.ftn.siit.sbnz.proj.sbnz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uns.ftn.siit.sbnz.proj.sbnz.model.PonudaUseva;
import uns.ftn.siit.sbnz.proj.sbnz.repository.PonudeUsevaRepositiry;

@Service
public class PonudeUsevaService {

    private PonudeUsevaRepositiry ponudeUsevaRepositiry;

    @Autowired
    public PonudeUsevaService(PonudeUsevaRepositiry ponudeUsevaRepositiry) {
        this.ponudeUsevaRepositiry = ponudeUsevaRepositiry;
    }

    public PonudaUseva getOne(Long id){
        return ponudeUsevaRepositiry.findById(id).orElse(null);
    }
}
