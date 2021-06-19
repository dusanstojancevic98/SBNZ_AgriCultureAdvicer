package uns.ftn.siit.sbnz.proj.sbnz.repository;

import jdk.nashorn.internal.runtime.logging.Logger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uns.ftn.siit.sbnz.proj.sbnz.model.UsevPodaci;

@Repository
public interface UsevPodaciRepository extends JpaRepository<UsevPodaci, Long> {
}
