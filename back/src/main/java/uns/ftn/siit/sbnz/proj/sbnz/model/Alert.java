package uns.ftn.siit.sbnz.proj.sbnz.model;

import com.sun.xml.bind.v2.model.core.ID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Alert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String opis;

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
