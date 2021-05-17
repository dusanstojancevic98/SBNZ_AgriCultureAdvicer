package uns.ftn.siit.sbnz.proj.sbnz.medjuklase;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class Povrsina {

    enum TipPovrsine{
        SMALL, LARGE
    }

    @Getter
    @Setter
    private TipPovrsine tipPovrsine;
}
