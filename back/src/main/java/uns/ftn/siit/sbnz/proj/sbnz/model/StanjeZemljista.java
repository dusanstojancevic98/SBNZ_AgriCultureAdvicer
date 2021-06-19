package uns.ftn.siit.sbnz.proj.sbnz.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uns.ftn.siit.sbnz.proj.sbnz.model.enums.TipStanjaZemljista;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StanjeZemljista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime datum = LocalDateTime.now();

    private double procenatVlage;

    private TipStanjaZemljista stanje;

    @OneToOne
    @JoinColumn(name = "prethodno_id", referencedColumnName = "id")
    private StanjeZemljista prethodnoStanje;
}
