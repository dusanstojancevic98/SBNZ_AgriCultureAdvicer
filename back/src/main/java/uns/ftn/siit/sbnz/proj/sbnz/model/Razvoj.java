package uns.ftn.siit.sbnz.proj.sbnz.model;

import com.sun.xml.bind.v2.model.core.ID;
import lombok.*;
import org.kie.api.definition.type.Key;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import javax.persistence.*;
import java.util.ArrayList;
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

    private String naziv;

    private  StanjeRazvoja stanjeRazvoja = StanjeRazvoja.INICIJALNO;

    @NonNull
    @ManyToOne(targetEntity = Korisnik.class)
    private Korisnik vlasnik;

    @OneToOne(cascade = CascadeType.ALL)
    private PonudaUseva ponudaUseva = new PonudaUseva();

    @OneToOne
    private Usev odabraniUsev;


    @Getter
    @Setter
    @OneToMany(cascade = CascadeType.ALL)
    List<StanjeUseva> stanjaUseva = new ArrayList<>();

    private Double budzet;

    private Date pocetakSadjenja;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable
    private List<Akcija> istorijaAkcija;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable
    private List<Akcija> trenutnaAkcija;

    @OneToOne(cascade = CascadeType.ALL)
    private Konfiguracija konfiguracija;


    public enum StanjeRazvoja{
        INICIJALNO, SPREMNO,  U_TOKU, PAUZIRANO, ZAVRSENO
    }


    private void setOdabraniUsev(Usev odabraniUsev) {
        this.odabraniUsev = odabraniUsev;

    }


    public void dodajAkciju(Akcija akcija){
        this.trenutnaAkcija.add(akcija);
    }

    public void zavrsiAkciju(Long id){
        Akcija akcija = trenutnaAkcija.stream().filter(a->a.getId().equals(id)).findFirst().orElseThrow(ResourceNotFoundException::new);
        akcija.setStanjeAkcije(Akcija.StanjeAkcije.OBAVLJENA);
    }

    public void otkaziAkciju(Long id){
        Akcija akcija = trenutnaAkcija.stream().filter(a->a.getId().equals(id)).findFirst().orElseThrow(ResourceNotFoundException::new);
        akcija.setStanjeAkcije(Akcija.StanjeAkcije.NEOBAVLJENA);
    }


    public void odaberiUsev(Long id) throws Exception {
        setOdabraniUsev(ponudaUseva.getPonude().stream().filter(u->u.getId().equals(id)).findFirst().orElseThrow(Exception::new));
        setStanjeRazvoja(StanjeRazvoja.SPREMNO);
    }
}
