package uns.ftn.siit.sbnz.proj.sbnz.model;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
@Table(name = "ponude_useva")
public class PonudaUseva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "ponuda_usev")
    private List<Usev> ponude = new ArrayList<>();

    public void addUsev(Usev usev){
        ponude.add(usev);
    }
}
