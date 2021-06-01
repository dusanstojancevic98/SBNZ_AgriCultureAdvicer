package uns.ftn.siit.sbnz.proj.sbnz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uns.ftn.siit.sbnz.proj.sbnz.model.Korisnik;

public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {

    public Korisnik findByUsername(String username);
    public Korisnik findByEmail(String email);
}
