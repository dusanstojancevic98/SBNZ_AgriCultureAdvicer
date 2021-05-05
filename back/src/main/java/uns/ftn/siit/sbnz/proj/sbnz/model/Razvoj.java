package uns.ftn.siit.sbnz.proj.sbnz.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class Razvoj {

    @Getter
    @Setter
    private String vlasnik;

    @Setter
    @Getter
    private Usev odabraniUsev;
}
