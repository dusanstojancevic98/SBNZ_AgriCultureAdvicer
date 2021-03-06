package uns.ftn.siit.sbnz.proj.sbnz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import uns.ftn.siit.sbnz.proj.sbnz.dto.RazvojResponse;
import uns.ftn.siit.sbnz.proj.sbnz.model.Korisnik;
import uns.ftn.siit.sbnz.proj.sbnz.model.Razvoj;
import uns.ftn.siit.sbnz.proj.sbnz.model.StanjeZemljista;
import uns.ftn.siit.sbnz.proj.sbnz.service.RazvojService;

import java.util.List;


@RestController
@RequestMapping(value = "/api/razvoj", produces = MediaType.APPLICATION_JSON_VALUE)
public class RazvojController {

    private final RazvojService razvojService;

    @Autowired
    public RazvojController(RazvojService razvojService) {
        this.razvojService = razvojService;
    }


    @GetMapping(value = "/{status}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RazvojResponse>> getRazvoji(@PathVariable Razvoj.StanjeRazvoja status, Authentication authentication) {
        Korisnik logged = (Korisnik) authentication.getPrincipal();

        return new ResponseEntity<>(razvojService.getAll(logged.getId(), status), HttpStatus.OK);
    }

    @GetMapping(value = "/trenutni/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RazvojResponse> getRazvoj(@PathVariable Long id, Authentication authentication) {
        Korisnik logged = (Korisnik) authentication.getPrincipal();
        return new ResponseEntity<>(razvojService.getOneByUserId(logged.getId(), id), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Razvoj>> getRazvoji(@PathVariable Long id) {
        razvojService.obrisiRazvoj(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/odaberi/{id}/{usevId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> odaberiUsevRazvoj(@PathVariable Long id, @PathVariable Long usevId) throws Exception {

        razvojService.odaberiUsev(id, usevId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/pokreni/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> pokreniRazvoj(@PathVariable Long id) {

        razvojService.pokreni(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> addRazvoj(@RequestBody Razvoj razvoj, Authentication authentication) {

        Korisnik logged = (Korisnik) authentication.getPrincipal();

        return new ResponseEntity<>(razvojService.addOne(razvoj, logged), HttpStatus.CREATED);
    }

    @PostMapping("/stanje-zemljista/{rid}")
    public void addStanjeZemljista(@PathVariable Long rid, @RequestBody StanjeZemljista stanjeZemljista){
        razvojService.addStanjeZemljista(rid, stanjeZemljista);
    }



    @GetMapping(value = "/fastForward/{id}/{time}")
    public ResponseEntity<Boolean> unapredVreme(@PathVariable Long id, @PathVariable Integer time){
        razvojService.ubrzajVreme(id, time);
        return new ResponseEntity<>(Boolean.TRUE,HttpStatus.OK);
    }
}
