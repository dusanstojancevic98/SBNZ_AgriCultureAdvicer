package uns.ftn.siit.sbnz.proj.sbnz.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class Zemljiste {

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
    private TipZemljista tipZemljista;
}
