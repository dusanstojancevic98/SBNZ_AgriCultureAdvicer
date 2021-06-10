package uns.ftn.siit.sbnz.proj.sbnz.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uns.ftn.siit.sbnz.proj.sbnz.dto.AkcijaResponse;
import uns.ftn.siit.sbnz.proj.sbnz.dto.AlertResponse;
import uns.ftn.siit.sbnz.proj.sbnz.model.Akcija;
import uns.ftn.siit.sbnz.proj.sbnz.model.Alert;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AlertMapper extends Mapper<Alert, AlertResponse, Object> {

    private AkcijaMapper akcijaMapper;

    @Autowired
    public AlertMapper(AkcijaMapper akcijaMapper) {
        this.akcijaMapper = akcijaMapper;
    }

    @Override
    public AlertResponse toResponse(Alert alert) {

        Long razvojId;
        if (alert.getRazvoj() == null) {
            razvojId = null;
        } else {
            razvojId = alert.getRazvoj().getId();
        }
        Long korisnikId;
        if (alert.getKorisnik() == null) {
            korisnikId = null;
        } else {
            korisnikId = alert.getKorisnik().getId();
        }
        List<AkcijaResponse> akcije = akcijaMapper.fromEntityList(alert.getAkcije());
        return new AlertResponse(alert.getId(), alert.getNaziv(), alert.getOpis(), akcije, razvojId, korisnikId,alert.getUticaj());
    }

    @Override
    public Alert fromRequest(Object dto) {
        return null;
    }

    @Override
    public Alert fromResponse(AlertResponse dto) {
        return null;
    }
}
