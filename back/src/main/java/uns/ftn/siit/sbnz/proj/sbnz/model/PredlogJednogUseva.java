package uns.ftn.siit.sbnz.proj.sbnz.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;


@AllArgsConstructor
@Getter
@Setter
public class PredlogJednogUseva {

    private Usev usev;

    private List<Bodovi> bodovi;
}
