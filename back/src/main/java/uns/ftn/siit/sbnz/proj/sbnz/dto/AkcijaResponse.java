package uns.ftn.siit.sbnz.proj.sbnz.dto;

import lombok.*;
import uns.ftn.siit.sbnz.proj.sbnz.model.Akcija;
import uns.ftn.siit.sbnz.proj.sbnz.model.Razvoj;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AkcijaResponse {

    private Long id;

    private String naziv;

    private String opisAkcije;

    private Akcija.StanjeAkcije stanjeAkcije = Akcija.StanjeAkcije.NOVA;

    private Long prethodnaAkcijaId;

    private Long razvojId;

    private LocalDateTime datumKreiranja;

    private LocalDateTime datumOdluke;
}
