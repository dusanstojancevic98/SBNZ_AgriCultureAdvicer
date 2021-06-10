package uns.ftn.siit.sbnz.proj.sbnz.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uns.ftn.siit.sbnz.proj.sbnz.model.*;
import uns.ftn.siit.sbnz.proj.sbnz.model.enums.TipZemljista;
import uns.ftn.siit.sbnz.proj.sbnz.service.OdabirUsevaService;
import uns.ftn.siit.sbnz.proj.sbnz.service.PonudeUsevaService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path="/api/ponudeUseva", produces = MediaType.APPLICATION_JSON_VALUE)
public class OdabirUsevaController {

    private final OdabirUsevaService odabirUsevaService;

    private PonudeUsevaService ponudeUsevaService;

    @Autowired
    public OdabirUsevaController(OdabirUsevaService odabirUsevaService, PonudeUsevaService ponudeUsevaService) {
        this.odabirUsevaService = odabirUsevaService;
        this.ponudeUsevaService = ponudeUsevaService;
    }

    @GetMapping()
    ResponseEntity<List<Usev>> getUsev(HttpServletRequest request){
        // TEST
        Zemljiste zemljiste = new Zemljiste();
        zemljiste.setPovrsina(80);
        zemljiste.setNagibZemljista(3);
        zemljiste.setDuzina(1);
        zemljiste.setSirina(1);
        zemljiste.setPhVrednost(8.5);
        zemljiste.setNadmorskaVisina(100);
        zemljiste.setProcenatHumusa(10);
        zemljiste.setProcenatKarbonata(1);
        zemljiste.setTipZemljista(TipZemljista.CRNICA);

        VremenskiPodaci vremenskiPodaci = new VremenskiPodaci();
        vremenskiPodaci.setProsecnaGodisnjaTemperatura(10);
        vremenskiPodaci.setPadavine(100);


        Konfiguracija konfiguracija = new Konfiguracija("Razvoj", zemljiste, vremenskiPodaci);

        Razvoj razvoj = new Razvoj();

        razvoj.setKonfiguracija(konfiguracija);
        //KieSession sesija = (KieSession) request.getSession().getAttribute("sesija");

        //odabirUsevaService.setKieSession(sesija);
        Razvoj razvoj1 = odabirUsevaService.preporuciUseve(razvoj);

        //request.getSession().setAttribute("sesija", odabirUsevaService.getKieSession());

        return new ResponseEntity<>(HttpStatus.OK);

    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PonudaUseva> getPonudaUseva(@PathVariable Long id){
        return new ResponseEntity<>(ponudeUsevaService.getOne(id), HttpStatus.OK);
    }

    @GetMapping(value = "/fastFoward/{time}")
    public ResponseEntity<Boolean> unapredVreme(@PathVariable Integer time){
        odabirUsevaService.ubrzajVreme(time);
        return new ResponseEntity<>(Boolean.TRUE,HttpStatus.OK);
    }

    @GetMapping(path = "/akcije")
    ResponseEntity<List<Akcija>> getAkcija(HttpServletRequest request){
        // TEST


        Razvoj razvoj = new Razvoj();
        razvoj.setTrenutnaAkcija(new ArrayList<>());

        UsevPodaci podaci = new UsevPodaci();
        StanjeUseva stanje1 = new StanjeUseva();
        stanje1.setStanja(new ArrayList<>());
        stanje1.getStanja().add(Uslov.PrisustvoPalamide);
        stanje1.getStanja().add(Uslov.ZutiList);


        StanjeUseva stanje2 = new StanjeUseva();
        stanje2.setStanja(new ArrayList<>());
        stanje2.getStanja().add(Uslov.IskrivljenList);
        stanje2.getStanja().add(Uslov.ZutiList);

        StanjeUseva stanje3 = new StanjeUseva();
        stanje3.setStanja(new ArrayList<>());
        stanje3.getStanja().add(Uslov.PisustvoBrsljena);
        stanje3.getStanja().add(Uslov.ZutiList);

        podaci.getStanjaUseva().add(stanje1);
        podaci.getStanjaUseva().add(stanje2);
        podaci.getStanjaUseva().add(stanje3);

        podaci.setUsev(new Usev("Breskva", "SuperBreskva"));

        //odabirUsevaService.setKieSession(sesija);
        List<Akcija> akcije = odabirUsevaService.dodajStanje(razvoj, podaci);

        //request.getSession().setAttribute("sesija", odabirUsevaService.getKieSession());

        return new ResponseEntity<>(akcije, HttpStatus.OK);

    }
}
