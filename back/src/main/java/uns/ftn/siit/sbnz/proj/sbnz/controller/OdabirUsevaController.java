package uns.ftn.siit.sbnz.proj.sbnz.controller;


import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uns.ftn.siit.sbnz.proj.sbnz.model.*;
import uns.ftn.siit.sbnz.proj.sbnz.service.OdabirUsevaService;

@RestController
@RequestMapping(path="/odabirUseva", produces = MediaType.APPLICATION_JSON_VALUE)
public class OdabirUsevaController {

    @Autowired
    private OdabirUsevaService odabirUsevaService;

    @GetMapping()
    ResponseEntity<String> getUsev(){
        Zemljiste zemljiste = new Zemljiste(101, 10,1, 10,12, 20,2, 1, TipZemljista.CRNICA);
        VremenskiPodaci vremenskiPodaci = new VremenskiPodaci(201);

        Konfiguracija konfiguracija = new Konfiguracija("Razvoj", zemljiste, vremenskiPodaci);

        Razvoj razvoj = new Razvoj("Milivoje",null);

        Usev usev = odabirUsevaService.odaberiUsev(konfiguracija, razvoj);

        return new ResponseEntity<>(usev.getNaziv(), HttpStatus.OK);

    }
}
