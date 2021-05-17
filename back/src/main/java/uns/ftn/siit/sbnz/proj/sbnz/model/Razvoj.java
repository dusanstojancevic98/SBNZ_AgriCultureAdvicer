package uns.ftn.siit.sbnz.proj.sbnz.model;

import com.sun.xml.bind.v2.model.core.ID;
import lombok.*;
import org.kie.api.definition.type.Key;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "razvoji")
public class Razvoj {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @NonNull
    private String vlasnik;

    @Getter
    @Setter
    @OneToOne
    private PonudaUseva ponudaUseva = new PonudaUseva();

    @Setter
    @Getter
    @OneToOne
    private Usev odabraniUsev;


    @Getter
    @Setter
    private Double budzet;

    @Getter
    @Setter
    private Date pocetakSadjenja;


    @Getter
    @Setter
    @OneToMany
    @JoinTable
    private List<Akcija> istorijaAkcija;

    @Getter
    @Setter
    @OneToMany
    @JoinTable
    private List<Akcija> trenutnaAkcija;

    @Setter
    @Getter
    @OneToOne
    private Konfiguracija konfiguracija;
}
