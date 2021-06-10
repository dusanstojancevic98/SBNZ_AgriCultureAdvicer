package uns.ftn.siit.sbnz.proj.sbnz.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uns.ftn.siit.sbnz.proj.sbnz.model.Akcija;
import uns.ftn.siit.sbnz.proj.sbnz.model.Alert;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlertResponse {
    private Long id;

    private String naziv;

    private String opis;

    private List<AkcijaResponse> akcije;

    private Long razvojId;

    private Long korisnikId;

    private Alert.Uticaj uticaj;

}
