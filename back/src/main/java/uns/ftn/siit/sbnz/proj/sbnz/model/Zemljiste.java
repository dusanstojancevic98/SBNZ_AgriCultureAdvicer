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
public class Zemljiste {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private double povrsina;

    @Getter
    @Setter
    private double sirina;

    @Getter
    @Setter
    private double duzina;

    @Getter
    @Setter
    private double nadmVisina;

    @Getter
    @Setter
    private double phVrednost;

    @Getter
    @Setter
    private double procenatHumusa;

    @Getter
    @Setter
    private double procenatKarbonata;

    @Getter
    @Setter
    private double nagibZemljista;

    @Getter
    @Setter
    private TipLokacije lokacija;

    @Getter
    @Setter
    private double nadmorskaVisina;

    @Getter
    @Setter
    @OneToMany
    @JoinTable(name = "zemljiste_usevi")
    private List<UsevPodaci> prethodniUsevi = new ArrayList<>();

    @Getter
    @Setter
    private TipZemljista tipZemljista;
}
