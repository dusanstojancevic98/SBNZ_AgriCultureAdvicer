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
public class Usev {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @NonNull
    private String naziv;

    @Getter
    @Setter
    @NonNull
    private String opis;

    @Getter
    @Setter
    private TipUseva tipUseva;

    @Getter
    @Setter
    @Transient
    private PodTipUseva podTipUseva;

}
