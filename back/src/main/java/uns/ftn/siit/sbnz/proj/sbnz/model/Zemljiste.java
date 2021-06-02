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


    @OneToMany
    @JoinTable(name = "zemljiste_usevi")
    private List<UsevPodaci> prethodniUsevi = new ArrayList<>();


    private TipZemljista tipZemljista;
}
