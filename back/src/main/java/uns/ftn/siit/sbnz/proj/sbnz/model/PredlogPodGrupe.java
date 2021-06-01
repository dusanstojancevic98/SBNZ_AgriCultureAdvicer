package uns.ftn.siit.sbnz.proj.sbnz.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uns.ftn.siit.sbnz.proj.sbnz.model.enums.PodTipUseva;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PredlogPodGrupe {

    private PodTipUseva podTipUseva;
    private List<Usev> usevi;
}
