package uns.ftn.siit.sbnz.proj.sbnz.medjuklase;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uns.ftn.siit.sbnz.proj.sbnz.model.enums.TipKlime;
import uns.ftn.siit.sbnz.proj.sbnz.model.enums.TipNagiba;

@NoArgsConstructor
@AllArgsConstructor
public class KategorijaKlime {
    @Getter
    @Setter
    private TipKlime tipKlime;
}
