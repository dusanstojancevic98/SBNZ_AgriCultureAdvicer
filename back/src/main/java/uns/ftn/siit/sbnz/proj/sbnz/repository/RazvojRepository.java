package uns.ftn.siit.sbnz.proj.sbnz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uns.ftn.siit.sbnz.proj.sbnz.model.Razvoj;

@Repository
public interface RazvojRepository extends JpaRepository<Razvoj, Long> {
}
