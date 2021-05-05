package uns.ftn.siit.sbnz.proj.sbnz.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class Konfiguracija {

    @Setter
    @Getter
    private String naziv;

    @Getter
    @Setter
    private Zemljiste zemljiste;

    @Getter
    @Setter
    private VremenskiPodaci vremenskiPodaci;
}
