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
@Setter
@Getter
public class Razvoj {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    private  StanjeRazvoja stanjeRazvoja;

    @NonNull
    @ManyToOne
    private Korisnik vlasnik;

    @OneToOne
    private PonudaUseva ponudaUseva = new PonudaUseva();

    @OneToOne
    private Usev odabraniUsev;


    private Double budzet;

    private Date pocetakSadjenja;


    @OneToMany
    @JoinTable
    private List<Akcija> istorijaAkcija;

    @OneToMany
    @JoinTable
    private List<Akcija> trenutnaAkcija;

    @OneToOne
    private Konfiguracija konfiguracija;


    public enum StanjeRazvoja{
        INICIJALNO, U_TOKU, PAUZIRANO, ZAVRSENO
    }


    public void setOdabraniUsev(Usev odabraniUsev) {
        this.odabraniUsev = odabraniUsev;

    }
}
