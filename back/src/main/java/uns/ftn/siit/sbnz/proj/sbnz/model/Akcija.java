package uns.ftn.siit.sbnz.proj.sbnz.model;


import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "akcija")
public class Akcija {


    @Id
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
    private String opisAkcije;
}
