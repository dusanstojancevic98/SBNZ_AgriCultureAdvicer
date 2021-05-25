package uns.ftn.siit.sbnz.proj.sbnz.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class UsloviAkcija {
    private Akcija akcija;
    private List<Uslov> uslovi;
    private Integer ponavljanje;
}
