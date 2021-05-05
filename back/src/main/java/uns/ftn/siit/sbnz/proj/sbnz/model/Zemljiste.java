package uns.ftn.siit.sbnz.proj.sbnz.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class Zemljiste {

    enum TipZemljista {
        CRNICA, GAJNJACA, SMONICA, ALUVIJALNO, PEPELJUSA, CRVENICA
    }

    @Getter
    @Setter
    private double povrsina;
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
    private TipZemljista tipZemljista;
}
