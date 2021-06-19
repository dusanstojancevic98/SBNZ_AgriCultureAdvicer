package uns.ftn.siit.sbnz.proj.sbnz.config;


import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.time.SessionPseudoClock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.*;
import org.springframework.web.context.annotation.SessionScope;
import uns.ftn.siit.sbnz.proj.sbnz.model.Akcija;
import uns.ftn.siit.sbnz.proj.sbnz.model.Uslov;
import uns.ftn.siit.sbnz.proj.sbnz.model.UsloviAkcija;
import uns.ftn.siit.sbnz.proj.sbnz.model.VremenskaPrognoza;
import uns.ftn.siit.sbnz.proj.sbnz.model.enums.TipPadavine;
import uns.ftn.siit.sbnz.proj.sbnz.service.AlertService;
import uns.ftn.siit.sbnz.proj.sbnz.model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Configuration
@ComponentScan("uns.ftn.siit.sbnz.proj.sbnz.service")
public class KieConfiguration {

    @Bean
    public KieContainer kieContainer(){
        KieServices ks = KieServices.Factory.get();
        KieContainer kc = ks.getKieClasspathContainer();
        return kc;
    }

    @Autowired
    private AlertService alertService;


    @Bean("basic")
    @SessionScope
    public KieSession kieSession() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks
                .newKieContainer(ks.newReleaseId("sbnz.integracija", "kjar", "0.0.1-SNAPSHOT"));
        return kContainer.newKieSession("basicSession");
    }

    @Bean("akcije")
    @Primary
    @Scope("prototype")
//  TODO  @SessionScope
    public KieSession kieSessionAkcije() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks
                .newKieContainer(ks.newReleaseId("sbnz.integracija", "kjar", "0.0.1-SNAPSHOT"));
        KieScanner kScanner = ks.newKieScanner(kContainer);
        kScanner.start(5_000);
        KieSession session = kContainer.newKieSession("cepConfigKsessionPseudoClock");
        insertUsloviAkcije(session);
        return session;
    }

    private Razvoj generateRazvoj(){
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
        return razvoj;
    }

    private void insertUsloviAkcije(KieSession session){
        Razvoj razvoj = generateRazvoj();
        List<Uslov> uslovi = new ArrayList<>();
        uslovi.add(Uslov.ZutiList);
        uslovi.add(Uslov.PrisustvoPalamide);
        UsloviAkcija uslov = new UsloviAkcija(new Akcija("prskaj", "Prskanje protiv palamide i zutog lista je obavezno" +
                "da bi usev uspeo da se oporavi od bolesti", razvoj), uslovi, 2  );
        session.insert(uslov);


        uslovi = new ArrayList<>();
        uslovi.add(Uslov.IskrivljenList);
        uslovi.add(Uslov.BraonList);
        uslov = new UsloviAkcija(new Akcija("zalivanje", "Potrebno je zaliti biljku jer zbog nedostatka vode" +
                " list pocinje da kovrdza", razvoj), uslovi, 2  );
        session.insert(uslov);



        uslovi = new ArrayList<>();
        uslovi.add(Uslov.PisustvoBrsljena);
        uslovi.add(Uslov.PrisustvoVisokogKorova);
        uslovi.add(Uslov.PrisustvoPalamide);
        uslov = new UsloviAkcija(new Akcija("prskanje", "Potrebno je isprskati biljku herbicidom" +
                " zbog pojave korova koji steti biljci", razvoj), uslovi, 3  );
        session.insert(uslov);



        uslovi = new ArrayList<>();
        uslovi.add(Uslov.PrisustvoLarvi);
        uslovi.add(Uslov.PrisustvoStonoga);
        uslovi.add(Uslov.PrisustvoMuva);
        uslovi.add(Uslov.PojedenList);
        uslov = new UsloviAkcija(new Akcija("prskanje", "Potrebno je isprskati biljku insekticidom" +
                " zbog pojave insekata", razvoj), uslovi, 3  );
        session.insert(uslov);



        uslovi = new ArrayList<>();
        uslovi.add(Uslov.IskrivljenList);
        uslovi.add(Uslov.BraonList);
        uslov = new UsloviAkcija(new Akcija("djubrenje", "Potrebno je nadjubriti zemljiste" +
                " SKN (sumpor, kalijum, azot) vestackim djubrivom", razvoj), uslovi, 2  );
        session.insert(uslov);
        new Thread(() -> generisiVremenskuPrognozu(session) ).start();
        session.setGlobal("alertService", alertService);

    }

    private void generisiVremenskuPrognozu(KieSession session) {
        Random rng = new Random();
        SessionPseudoClock clock = session.getSessionClock();
        session.getSessionClock();
        LocalDateTime pocetak = LocalDateTime.now();
        while(true){
            VremenskaPrognoza vp = new VremenskaPrognoza();
            vp.setTipPadavine(getTipPadavine(rng));
            vp.setMaximalnaTemperatura(20 + rng.nextInt(15));
            vp.setMaximalnaTemperatura(-10 + rng.nextInt(20));
            vp.setJacinaVetra(4 + rng.nextInt(5));
            vp.setKolicinaPadavina(2 + rng.nextInt(4));
            session.insert(vp);
            vp.setDatum(pocetak);

            pocetak = pocetak.plusDays(1);
            clock.advanceTime(1, TimeUnit.DAYS);

            try {
                Thread.sleep(5000);
            }catch (Exception e){
                return;
            }

        }
    }


    private TipPadavine getTipPadavine(Random rng){
         return TipPadavine.values()[rng.nextInt(5)];
    }
}
