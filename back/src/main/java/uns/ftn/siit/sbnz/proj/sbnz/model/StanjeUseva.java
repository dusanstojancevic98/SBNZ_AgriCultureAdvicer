package uns.ftn.siit.sbnz.proj.sbnz.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uns.ftn.siit.sbnz.proj.sbnz.model.enums.Sezona;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "stanja_useva")
public class StanjeUseva {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Getter
    @Setter
    @ManyToOne
    UsevPodaci usevPodaci;

    @Getter
    @Setter
    private LocalDateTime date;

    @Getter
    @Setter
    private String oblikLista;

    @Setter
    @Getter
    private String bojaLista;

    @Setter
    @Getter
    private Sezona sezonaUseva;

    @Setter
    @Getter
    private double visinaKorova;


    @Getter
    @Setter
    private boolean prisustvoInsekta=false;


    @Getter
    @Setter
    @OneToMany
    private List<Preparat> preporucenaZastita;


}
