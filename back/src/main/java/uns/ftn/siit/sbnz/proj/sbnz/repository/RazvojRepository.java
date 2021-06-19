package uns.ftn.siit.sbnz.proj.sbnz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uns.ftn.siit.sbnz.proj.sbnz.model.Razvoj;

import java.util.List;

@Repository
public interface RazvojRepository extends JpaRepository<Razvoj, Long> {
    public List<Razvoj> getAllByVlasnikIdAndStanjeRazvoja(Long aLong, Razvoj.StanjeRazvoja stanjeRazvoja);
    public Razvoj getByVlasnikIdAndId(Long aLong, Long id);
    public List<Razvoj> getAllByStanjeRazvoja(Razvoj.StanjeRazvoja stanjeRazvoja);

}
