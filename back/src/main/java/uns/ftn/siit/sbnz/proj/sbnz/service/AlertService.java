package uns.ftn.siit.sbnz.proj.sbnz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uns.ftn.siit.sbnz.proj.sbnz.dto.AlertResponse;
import uns.ftn.siit.sbnz.proj.sbnz.dto.CountDto;
import uns.ftn.siit.sbnz.proj.sbnz.exceptions.BadRequestException;
import uns.ftn.siit.sbnz.proj.sbnz.exceptions.ResourceNotFoundException;
import uns.ftn.siit.sbnz.proj.sbnz.mappers.AlertMapper;
import uns.ftn.siit.sbnz.proj.sbnz.model.Alert;
import uns.ftn.siit.sbnz.proj.sbnz.repository.AlertRepository;

import java.util.List;

@Service
public class AlertService {

    private final AlertRepository alertRepository;
    private final AlertMapper alertMapper;

    @Autowired
    public AlertService(KorisnikService korisnikService, AlertRepository alertRepository, AlertMapper alertMapper) {
        this.alertRepository = alertRepository;
        this.alertMapper = alertMapper;
    }

    public CountDto getAlertsCount(Long korisnikId){
        return new CountDto(alertRepository.countAlertsByKorisnikIdAndPogledanoIsFalse(korisnikId));
    }
    public List<AlertResponse> getAlertsByKorisnikId(Long korisnikId){
        return alertMapper.fromEntityList(alertRepository.getAlertsByKorisnikIdAndPogledanoIsFalse(korisnikId));
    }

    public List<AlertResponse> getAlertsByRazvojId(Long razvojId){
        return alertMapper.fromEntityList(alertRepository.getAlertsByRazvojIdAndPogledanoIsFalse(razvojId));
    }

    public void pregledAlert(Long id, Long aLong) {
        Alert alert = alertRepository.findByIdAndKorisnikId(id, aLong).orElseThrow(() -> new BadRequestException("Los zahtev za pregled alert-a"));
        alert.setPogledano(true);
        alertRepository.save(alert);
    }
}
