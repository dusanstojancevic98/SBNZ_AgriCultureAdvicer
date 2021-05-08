package uns.ftn.siit.sbnz.proj.sbnz.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uns.ftn.siit.sbnz.proj.sbnz.model.*;
import uns.ftn.siit.sbnz.proj.sbnz.service.OdabirUsevaService;

import java.util.List;

@RestController
@RequestMapping(path="/odabirUseva", produces = MediaType.APPLICATION_JSON_VALUE)
public class OdabirUsevaController {

    @Autowired
    private OdabirUsevaService odabirUsevaService;

    @GetMapping()
    ResponseEntity<List<Usev>> getUsev(){
        // TEST
        Zemljiste zemljiste = new Zemljiste();
        zemljiste.setPovrsina(80);
        zemljiste.setNagibZemljista(10);
        zemljiste.setDuzina(1);
        zemljiste.setSirina(1);
        zemljiste.setPhVrednost(6.5);
        zemljiste.setNadmVisina(100);
        zemljiste.setProcenatHumusa(10);
        zemljiste.setProcenatKarbonata(1);
        zemljiste.setTipZemljista(TipZemljista.CRNICA);

        VremenskiPodaci vremenskiPodaci = new VremenskiPodaci();
        vremenskiPodaci.setPadavine(100);


        Konfiguracija konfiguracija = new Konfiguracija("Razvoj", zemljiste, vremenskiPodaci);

        Razvoj razvoj = new Razvoj("Milivoje");

        razvoj.setKonfiguracija(konfiguracija);

        List<Usev> ponuda = odabirUsevaService.preporuciUseve(konfiguracija, razvoj);

        return new ResponseEntity<>(ponuda, HttpStatus.OK);

    }
}
