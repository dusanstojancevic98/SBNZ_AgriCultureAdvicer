package uns.ftn.siit.sbnz.proj.sbnz.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.kie.api.definition.rule.All;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    @OneToMany
    List<StanjeUseva> stanjaUseva = new ArrayList<>();

    @Getter
    @Setter
    @OneToMany
    private List<Preparat> preporucenaZastita;


    @Getter
    @Setter
    private LocalDateTime pocetakUzgoja;

    @Getter
    @Setter
    private LocalDateTime krajUzgoja;

    public UsevPodaci(Usev usev) {
        this.usev = usev;
    }
}
