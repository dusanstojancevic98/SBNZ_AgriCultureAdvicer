package uns.ftn.siit.sbnz.proj.sbnz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import uns.ftn.siit.sbnz.proj.sbnz.dto.AlertResponse;
import uns.ftn.siit.sbnz.proj.sbnz.dto.CountDto;
import uns.ftn.siit.sbnz.proj.sbnz.model.Korisnik;
import uns.ftn.siit.sbnz.proj.sbnz.service.AlertService;

import java.util.List;

@RestController
@RequestMapping("api/alerts")
public class AlertController {
    private final AlertService alertService;

    @Autowired
    public AlertController(AlertService alertService) {
        this.alertService = alertService;
    }

    @GetMapping(value = "/count")
    public ResponseEntity<CountDto> getAlertsCount(Authentication authentication) {
        Korisnik logged = (Korisnik) authentication.getPrincipal();
        return new ResponseEntity<>(alertService.getAlertsCount(logged.getId()), HttpStatus.OK);
    }

    @PatchMapping(value = "/{id}")
    public void pregledajAlert(@PathVariable Long id, Authentication authentication){
        Korisnik logged = (Korisnik) authentication.getPrincipal();
        alertService.pregledAlert(logged.getId(), id);
    }


    @GetMapping(value = "/razvoj/{razvojId}")
    public ResponseEntity<List<AlertResponse>> getAlertsByRazvojId(@PathVariable Long razvojId) {
        return new ResponseEntity<>(alertService.getAlertsByRazvojId(razvojId), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<AlertResponse>> getAlertsByKorisnikId(Authentication authentication) {
        Korisnik logged = (Korisnik) authentication.getPrincipal();
        return new ResponseEntity<>(alertService.getAlertsByKorisnikId(logged.getId()), HttpStatus.OK);
    }
}
