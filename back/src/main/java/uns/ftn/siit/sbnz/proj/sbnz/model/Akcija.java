package uns.ftn.siit.sbnz.proj.sbnz.model;


import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
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


    private LocalDateTime datumKreiranja = LocalDateTime.now();

    private LocalDateTime datumOdluke;

    public enum StanjeAkcije {
        NOVA, OBAVLJENA, NEOBAVLJENA
    }

    public void setStanjeAkcije(StanjeAkcije stanjeAkcije) {
        this.stanjeAkcije = stanjeAkcije;
        if (stanjeAkcije!=StanjeAkcije.NOVA){
            this.datumOdluke = LocalDateTime.now();
        }
    }
}
