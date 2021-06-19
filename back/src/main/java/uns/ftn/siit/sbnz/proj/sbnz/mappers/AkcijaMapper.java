package uns.ftn.siit.sbnz.proj.sbnz.mappers;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import uns.ftn.siit.sbnz.proj.sbnz.dto.AkcijaResponse;
import uns.ftn.siit.sbnz.proj.sbnz.model.Akcija;

@Component
public class AkcijaMapper extends Mapper<Akcija, AkcijaResponse, Object>{
    @Override
    public AkcijaResponse toResponse(Akcija akcija) {
        Long prethodni = null;
        if(akcija.getPrethodnaAkcija()!=null){
            prethodni = akcija.getPrethodnaAkcija().getId();
        }
        return new AkcijaResponse(
                akcija.getId(),
                akcija.getNaziv(),
                akcija.getOpisAkcije(),
                akcija.getStanjeAkcije(),
                prethodni,
                akcija.getDatumKreiranja(),
                akcija.getDatumOdluke()
        );
    }

    @Override
    public Akcija fromRequest(Object dto) {
        return null;
    }

    @Override
    public Akcija fromResponse(AkcijaResponse dto) {
        return null;
    }
}
