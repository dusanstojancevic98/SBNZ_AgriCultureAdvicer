package uns.ftn.siit.sbnz.proj.sbnz.model;

import com.sun.xml.bind.v2.model.core.ID;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
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


    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable
    private List<Akcija> istorijaAkcija = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Akcija> trenutnaAkcija = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    private Konfiguracija konfiguracija;

    public void addStanjeZemljista(StanjeZemljista stanjeZemljista) {
        Zemljiste zemljiste = konfiguracija.getZemljiste();
        zemljiste.azurirajStanjeZemljista(stanjeZemljista);
        konfiguracija.setZemljiste(zemljiste);
    }


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
