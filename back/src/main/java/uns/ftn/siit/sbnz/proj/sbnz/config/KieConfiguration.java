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
    @Scope("prototype")
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
        session.setGlobal("alertService", alertService);
        insertUsloviAkcije(session);
        return session;
    }


    private void insertUsloviAkcije(KieSession session){
        List<Uslov> uslovi = new ArrayList<>();
        uslovi.add(Uslov.ZutiList);
        uslovi.add(Uslov.PrisustvoPalamide);
        UsloviAkcija uslov = new UsloviAkcija(new Akcija("prskaj", "Prskanje protiv palamide i zutog lista je obavezno" +
                "da bi usev uspeo da se oporavi od bolesti"), uslovi, 2  );
        session.insert(uslov);


        uslovi = new ArrayList<>();
        uslovi.add(Uslov.IskrivljenList);
        uslovi.add(Uslov.BraonList);
        uslov = new UsloviAkcija(new Akcija("zalivanje", "Potrebno je zaliti biljku jer zbog nedostatka vode" +
                " list pocinje da kovrdza"), uslovi, 2  );
        session.insert(uslov);



        uslovi = new ArrayList<>();
        uslovi.add(Uslov.PisustvoBrsljena);
        uslovi.add(Uslov.PrisustvoVisokogKorova);
        uslovi.add(Uslov.PrisustvoPalamide);
        uslov = new UsloviAkcija(new Akcija("prskanje", "Potrebno je isprskati biljku herbicidom" +
                " zbog pojave korova koji steti biljci"), uslovi, 3  );
        session.insert(uslov);



        uslovi = new ArrayList<>();
        uslovi.add(Uslov.PrisustvoLarvi);
        uslovi.add(Uslov.PrisustvoStonoga);
        uslovi.add(Uslov.PrisustvoMuva);
        uslovi.add(Uslov.PojedenList);
        uslov = new UsloviAkcija(new Akcija("prskanje", "Potrebno je isprskati biljku insekticidom" +
                " zbog pojave insekata"), uslovi, 3  );
        session.insert(uslov);



        uslovi = new ArrayList<>();
        uslovi.add(Uslov.IskrivljenList);
        uslovi.add(Uslov.BraonList);
        uslov = new UsloviAkcija(new Akcija("djubrenje", "Potrebno je nadjubriti zemljiste" +
                " SKN (sumpor, kalijum, azot) vestackim djubrivom"), uslovi, 2  );
        session.insert(uslov);
    }




}
