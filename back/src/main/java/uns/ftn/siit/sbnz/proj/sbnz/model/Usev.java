package uns.ftn.siit.sbnz.proj.sbnz.model;

import lombok.*;

import javax.persistence.*;

@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usevi")
public class Usev {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @NonNull
    private String naziv;

    @Getter
    @Setter
    @NonNull
    private String opis;


}
