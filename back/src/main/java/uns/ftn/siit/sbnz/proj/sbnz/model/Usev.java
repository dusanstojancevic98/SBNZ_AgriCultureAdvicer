package uns.ftn.siit.sbnz.proj.sbnz.model;

import lombok.*;
import org.springframework.transaction.annotation.Transactional;
import uns.ftn.siit.sbnz.proj.sbnz.interfejsi.PodKategorijaUseva;
import uns.ftn.siit.sbnz.proj.sbnz.model.enums.PodTipUseva;
import uns.ftn.siit.sbnz.proj.sbnz.model.enums.Sezona;
import uns.ftn.siit.sbnz.proj.sbnz.model.enums.TipUseva;

import javax.persistence.*;
import java.util.List;


@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usevi")
@Getter
@Setter
public class Usev {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @NonNull
    private String naziv;

    @NonNull
    private String opis;

    private TipUseva tipUseva;

    @Transient
    private PodTipUseva podTipUseva;

}
