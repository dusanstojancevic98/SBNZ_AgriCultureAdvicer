package uns.ftn.siit.sbnz.proj.sbnz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uns.ftn.siit.sbnz.proj.sbnz.model.Akcija;

import java.util.Optional;

@Repository
public interface AkcijaRepository extends JpaRepository<Akcija, Long> {
    public Optional<Akcija> getAkcijaByIdAndRazvoj_VlasnikId(Long id, Long vlasnikId);
    public Optional<Akcija> getAkcijaByIdAndRazvojId(Long id, Long razvojId);
}
