package uns.ftn.siit.sbnz.proj.sbnz.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class PonudaUseva {

    @Getter
    @Setter
    private List<Usev> ponude = new ArrayList<>();

}
