package uns.ftn.siit.sbnz.proj.sbnz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import uns.ftn.siit.sbnz.proj.sbnz.dto.AlertResponse;
import uns.ftn.siit.sbnz.proj.sbnz.dto.CountDto;
import uns.ftn.siit.sbnz.proj.sbnz.exceptions.BadRequestException;
import uns.ftn.siit.sbnz.proj.sbnz.exceptions.ResourceNotFoundException;
import uns.ftn.siit.sbnz.proj.sbnz.mappers.AlertMapper;
import uns.ftn.siit.sbnz.proj.sbnz.model.Akcija;
import uns.ftn.siit.sbnz.proj.sbnz.model.Alert;
import uns.ftn.siit.sbnz.proj.sbnz.model.Razvoj;
import uns.ftn.siit.sbnz.proj.sbnz.repository.AkcijaRepository;
import uns.ftn.siit.sbnz.proj.sbnz.repository.AlertRepository;
import uns.ftn.siit.sbnz.proj.sbnz.repository.RazvojRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlertService {

    private final AlertRepository alertRepository;
    private final AlertMapper alertMapper;
    @Autowired
    private RazvojRepository razvojRepository;

    @Autowired
    private AkcijaRepository akcijaRepo;

    @Autowired
    private JavaMailSender emailSender;

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

    public void dodajAlert(Razvoj razvoj, Akcija akcija){
        razvojRepository.save(razvoj);
        razvoj.getTrenutnaAkcija().add(akcija);
        razvojRepository.save(razvoj);

        try{
            posaljiEmail(razvoj.getVlasnik().getEmail(), akcija);
        }catch (Exception e){
            e.printStackTrace();
        }
        List<Akcija> akcije = new ArrayList<>();
        akcije.add(akcija);
        Alert alert = new Alert(null, akcija.getNaziv(), akcija.getOpisAkcije(), akcije,razvoj, razvoj.getVlasnik(), Alert.Uticaj.NORMALAN, false);
        alertRepository.save(alert);
    }

    public void posaljiEmail(String email, Akcija akcija){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply.kts.l9@gmail.com");
        message.setTo(email);
        message.setSubject("Obavestenje o usevu");
        message.setText("Obavestavamo Vas, " +
                "\nPotrebno je da odradite: " + akcija.getOpisAkcije() + "." +
                "\nTakodje potvrdite da ste uradili akciju na nasem servisu.");
        emailSender.send(message);
    }
}
