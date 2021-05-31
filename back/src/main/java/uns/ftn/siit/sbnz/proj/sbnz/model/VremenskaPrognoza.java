package uns.ftn.siit.sbnz.proj.sbnz.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.kie.api.definition.type.Role;
import uns.ftn.siit.sbnz.proj.sbnz.model.enums.TipPadavine;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@NoArgsConstructor
@Entity
@Table(name = "vremenska_prognoza")
@Getter
@Setter
@Role(Role.Type.EVENT)
public class VremenskaPrognoza {

    @Id
    private Long id;

    private Date datum;
    private double maximalnaTemperatura;
    private double minimalnaTemperatura;
    private double kolicinaPadavina;
    private double vlaznostVazduha;
    private double vazdusniPritisak;
    private double jacinaVetra;
    private TipPadavine tipPadavine;

}
