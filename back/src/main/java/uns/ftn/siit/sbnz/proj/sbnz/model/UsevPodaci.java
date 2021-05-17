package uns.ftn.siit.sbnz.proj.sbnz.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.kie.api.definition.rule.All;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "podaci_useva")
public class UsevPodaci {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @OneToOne
    Usev usev;

    @Getter
    @Setter
    @OneToMany(mappedBy = "usevPodaci")
    List<StanjeUseva> stanjaUseva;

}
