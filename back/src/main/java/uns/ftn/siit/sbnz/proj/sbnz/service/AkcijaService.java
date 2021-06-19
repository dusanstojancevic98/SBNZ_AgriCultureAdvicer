package uns.ftn.siit.sbnz.proj.sbnz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uns.ftn.siit.sbnz.proj.sbnz.exceptions.BadRequestException;
import uns.ftn.siit.sbnz.proj.sbnz.model.Akcija;
import uns.ftn.siit.sbnz.proj.sbnz.repository.AkcijaRepository;

import java.util.Optional;

@Service
public class AkcijaService {

    private AkcijaRepository akcijaRepository;

    @Autowired
    public AkcijaService(AkcijaRepository akcijaRepository) {
        this.akcijaRepository = akcijaRepository;
    }


}
