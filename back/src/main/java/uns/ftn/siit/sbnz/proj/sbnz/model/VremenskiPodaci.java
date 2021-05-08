package uns.ftn.siit.sbnz.proj.sbnz.model;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@Entity
@Table(name = "vremenski_podaci")
public class VremenskiPodaci {

    @Getter
    @Setter
    @Id
    private Long id;

    @Setter
    @Getter
    private int brojSuncanihDana;

    @Setter
    @Getter
    private double padavine;


    @Setter
    @Getter
    private double prosecnaGodisnjaTemperatura;
}
