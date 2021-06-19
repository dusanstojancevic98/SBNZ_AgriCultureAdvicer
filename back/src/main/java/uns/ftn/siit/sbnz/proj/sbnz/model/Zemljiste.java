package uns.ftn.siit.sbnz.proj.sbnz.model;

import lombok.*;
import uns.ftn.siit.sbnz.proj.sbnz.model.enums.TipLokacije;
import uns.ftn.siit.sbnz.proj.sbnz.model.enums.TipZemljista;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
@Table(name = "zemljista")
@Getter
@Setter
public class Zemljiste {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;


    private double povrsina;


    private double sirina;


    private double duzina;


    private double phVrednost;


    private double procenatHumusa;


    private double procenatKarbonata;


    private double nagibZemljista;


    private TipLokacije lokacija;


    private double nadmorskaVisina;

    @OneToOne(cascade = CascadeType.ALL)
    private UsevPodaci trenutanUsev;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "zemljiste_usevi")
    private List<UsevPodaci> prethodniUsevi = new ArrayList<>();


    @OneToOne(cascade = CascadeType.ALL)
    private StanjeZemljista trenutnoStanjeZemljista;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "zemljiste_stanja")
    private List<StanjeZemljista> stanjaZemljista = new ArrayList<>();

    private TipZemljista tipZemljista;

    public void azurirajStanjeZemljista(StanjeZemljista stanjeZemljista){
        if(trenutnoStanjeZemljista!=null){
            stanjeZemljista.setPrethodnoStanje(trenutnoStanjeZemljista);
            stanjaZemljista.add(trenutnoStanjeZemljista);
        }
        trenutnoStanjeZemljista = stanjeZemljista;
    }

}

