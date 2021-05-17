package uns.ftn.siit.sbnz.proj.sbnz.model;

import lombok.*;
import uns.ftn.siit.sbnz.proj.sbnz.model.enums.Sezona;
import uns.ftn.siit.sbnz.proj.sbnz.model.enums.TipPreparata;

import javax.persistence.*;
import java.util.List;

@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usevi")
public class Usev {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @NonNull
    private String naziv;

    @Getter
    @Setter
    @NonNull
    private String opis;


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
    private boolean prisustvoInsekta;

    @Getter
    @Setter
    private List<TipPreparata> preporucenaZastita;


}
