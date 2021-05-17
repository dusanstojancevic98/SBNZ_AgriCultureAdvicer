package uns.ftn.siit.sbnz.proj.sbnz.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@NoArgsConstructor
@Entity
@Table(name = "akcija")
@Getter
@Setter
public class Akcija {

    private String naziv;
    private String opisAkcije;
}
