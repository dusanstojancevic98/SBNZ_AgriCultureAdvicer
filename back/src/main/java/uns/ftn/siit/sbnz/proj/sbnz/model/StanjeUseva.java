package uns.ftn.siit.sbnz.proj.sbnz.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uns.ftn.siit.sbnz.proj.sbnz.model.enums.Sezona;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "stanja_useva")
public class StanjeUseva {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private LocalDateTime date;

    @Getter
    @Setter
    @ElementCollection(targetClass = Uslov.class)
    @JoinTable(name = "tblUslovTypes", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "stanja")
    @Enumerated(EnumType.STRING)
    private List<Uslov> stanja;
}
