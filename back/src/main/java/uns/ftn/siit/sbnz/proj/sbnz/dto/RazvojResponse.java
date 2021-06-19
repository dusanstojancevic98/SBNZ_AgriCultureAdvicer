package uns.ftn.siit.sbnz.proj.sbnz.dto;

import lombok.*;
import uns.ftn.siit.sbnz.proj.sbnz.model.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RazvojResponse {
    private Long id;

    private String naziv;

    private Razvoj.StanjeRazvoja stanjeRazvoja = Razvoj.StanjeRazvoja.INICIJALNO;

    private PonudaUseva ponudaUseva = new PonudaUseva();

    private Usev odabraniUsev;

    private Double budzet;

    private List<AkcijaResponse> istorijaAkcija;

    private List<AkcijaResponse> trenutnaAkcija;

    private Konfiguracija konfiguracija;


}
