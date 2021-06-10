package uns.ftn.siit.sbnz.proj.sbnz.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uns.ftn.siit.sbnz.proj.sbnz.model.Alert;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlertResponse {
    private Long id;

    private String name;

    private String opis;

    private Long razvojId;

    private Long korisnikId;

    private Alert.Uticaj uticaj;

}
