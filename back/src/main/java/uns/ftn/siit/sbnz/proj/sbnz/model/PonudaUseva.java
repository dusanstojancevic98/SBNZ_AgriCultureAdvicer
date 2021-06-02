package uns.ftn.siit.sbnz.proj.sbnz.model;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
@Table(name = "ponude_useva")
@Getter
@Setter
public class PonudaUseva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "ponuda_usev")
    private List<Usev> ponude = new ArrayList<>();

    public void addUsev(Usev usev){
        ponude.add(usev);
    }
}
