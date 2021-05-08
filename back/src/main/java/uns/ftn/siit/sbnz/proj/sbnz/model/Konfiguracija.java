package uns.ftn.siit.sbnz.proj.sbnz.model;

import lombok.*;

import javax.persistence.*;
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "konfiguracije")
public class Konfiguracija {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    @NonNull
    private String naziv;

    @Getter
    @Setter
    @OneToOne
    @NonNull
    private Zemljiste zemljiste;

    @Getter
    @Setter
    @NonNull
    @OneToOne
    private VremenskiPodaci vremenskiPodaci;
}
