package uns.ftn.siit.sbnz.proj.sbnz.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uns.ftn.siit.sbnz.proj.sbnz.repository.KorisnikRepository;

@Service
public class KorisnikService {

    private KorisnikRepository korisnikRepository;

    @Autowired
    public KorisnikService(KorisnikRepository korisnikRepository) {
        this.korisnikRepository = korisnikRepository;
    }


}
