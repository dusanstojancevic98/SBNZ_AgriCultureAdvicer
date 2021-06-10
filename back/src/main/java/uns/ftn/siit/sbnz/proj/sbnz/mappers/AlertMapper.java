package uns.ftn.siit.sbnz.proj.sbnz.mappers;

import org.springframework.stereotype.Component;
import uns.ftn.siit.sbnz.proj.sbnz.dto.AlertResponse;
import uns.ftn.siit.sbnz.proj.sbnz.model.Alert;

@Component
public class AlertMapper extends Mapper<Alert, AlertResponse, Object> {
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
        return new AlertResponse(alert.getId(), alert.getName(), alert.getOpis(), razvojId, korisnikId,alert.getUticaj());
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
