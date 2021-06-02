package uns.ftn.siit.sbnz.proj.sbnz.model;

import lombok.*;

import javax.persistence.*;
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "konfiguracije")
@Getter
@Setter
public class Konfiguracija {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String naziv;


    @NonNull
    @OneToOne(cascade = CascadeType.ALL)
    private Zemljiste zemljiste;

    @NonNull
    @OneToOne(cascade = CascadeType.ALL)
    private VremenskiPodaci vremenskiPodaci;
}
