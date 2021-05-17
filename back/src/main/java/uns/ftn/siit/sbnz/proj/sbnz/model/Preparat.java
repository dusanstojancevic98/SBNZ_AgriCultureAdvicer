package uns.ftn.siit.sbnz.proj.sbnz.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uns.ftn.siit.sbnz.proj.sbnz.model.enums.TipPreparata;

import javax.persistence.*;

@NoArgsConstructor
@Table(name = "preparat")
@Entity
public class Preparat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private TipPreparata tipPreparata;

    @Getter
    @Setter
    private String naziv;

    @Getter
    @Setter
    private String opis;


}
