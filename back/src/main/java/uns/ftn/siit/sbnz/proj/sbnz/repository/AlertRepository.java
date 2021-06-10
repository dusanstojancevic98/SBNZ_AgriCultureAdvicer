package uns.ftn.siit.sbnz.proj.sbnz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uns.ftn.siit.sbnz.proj.sbnz.model.Alert;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Long> {
    public Optional<Alert> findByIdAndKorisnikId(Long id, Long korId);
    public Long countAlertsByKorisnikIdAndPogledanoIsFalse(Long korisnikId);
    public List<Alert> getAlertsByKorisnikIdAndPogledanoIsFalse(Long korisnikId);
    public List<Alert> getAlertsByRazvojIdAndPogledanoIsFalse(Long razvojId);
}
