package uns.ftn.siit.sbnz.proj.sbnz.model;


import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "akcija")
@Getter
@Setter
public class Akcija {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NonNull
    private String naziv;

    @NonNull
    private String opisAkcije;

    private StanjeAkcije stanjeAkcije = StanjeAkcije.NOVA;

    @OneToOne
    private Akcija prethodnaAkcija;


    public enum StanjeAkcije {
        NOVA, OBAVLJENA, NEOBAVLJENA
    }
}
