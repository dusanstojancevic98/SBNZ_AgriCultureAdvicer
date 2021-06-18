package uns.ftn.siit.sbnz.proj.sbnz.model;

import com.sun.xml.bind.v2.model.core.ID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Alert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String naziv;

    private String opis;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Akcija> akcije;

    @ManyToOne
    private Razvoj razvoj;

    @ManyToOne
    private Korisnik korisnik;

    private Uticaj uticaj;

    private boolean pogledano;

    public enum Uticaj{
        NORMALAN, UMEREN, JAK
    }
}
