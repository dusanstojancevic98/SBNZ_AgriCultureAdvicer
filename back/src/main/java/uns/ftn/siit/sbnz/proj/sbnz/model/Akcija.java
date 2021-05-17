package uns.ftn.siit.sbnz.proj.sbnz.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@Entity
@Table(name = "akcija")
@Getter
@Setter
public class Akcija {


    @Id
    @Getter
    @Setter
    private Long id;


    @Getter
    @Setter
    private String naziv;

    @Getter
    @Setter
    private String opisAkcije;
}
