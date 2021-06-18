package uns.ftn.siit.sbnz.proj.sbnz.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import uns.ftn.siit.sbnz.proj.sbnz.model.Korisnik;
import uns.ftn.siit.sbnz.proj.sbnz.model.Razvoj;
import uns.ftn.siit.sbnz.proj.sbnz.model.StanjeUseva;
import uns.ftn.siit.sbnz.proj.sbnz.service.OdabirUsevaService;
import uns.ftn.siit.sbnz.proj.sbnz.service.RazvojService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/stanja", produces = MediaType.APPLICATION_JSON_VALUE)
public class StanjaController {

    @Autowired
    private OdabirUsevaService usevService;


    @GetMapping(path = "{idRazvoja}")
    public ResponseEntity<List<StanjeUseva>> getStanja(@PathVariable Long idRazvoja) {
        return new ResponseEntity<>(usevService.getStanja(idRazvoja), HttpStatus.CREATED);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StanjeUseva> addStanje(@RequestBody StanjeUseva stanje) {
        usevService.dodajStanje(stanje);
        return new ResponseEntity<>(stanje, HttpStatus.CREATED);
    }

}
