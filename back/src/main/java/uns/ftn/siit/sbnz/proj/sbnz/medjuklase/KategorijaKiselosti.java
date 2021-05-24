package uns.ftn.siit.sbnz.proj.sbnz.medjuklase;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uns.ftn.siit.sbnz.proj.sbnz.model.enums.TipKiselosti;

@NoArgsConstructor
@AllArgsConstructor
public class KategorijaKiselosti {
    @Getter
    @Setter
    private TipKiselosti tipKiselosti;
}