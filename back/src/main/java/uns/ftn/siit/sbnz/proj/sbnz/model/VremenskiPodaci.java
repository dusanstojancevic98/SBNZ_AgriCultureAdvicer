package uns.ftn.siit.sbnz.proj.sbnz.model;


import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Table(name = "vremenski_podaci")
@Setter
@Getter
public class VremenskiPodaci {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int brojSuncanihDana;

    private double padavine;

    private double prosecnaGodisnjaTemperatura;
}
