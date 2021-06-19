package uns.ftn.siit.sbnz.proj.sbnz.config;


import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.context.annotation.*;
import org.springframework.web.context.annotation.SessionScope;
import uns.ftn.siit.sbnz.proj.sbnz.model.*;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ComponentScan("uns.ftn.siit.sbnz.proj.sbnz.service")
public class KieConfiguration {

    @Bean
    public KieContainer kieContainer(){
        KieServices ks = KieServices.Factory.get();
        KieContainer kc = ks.getKieClasspathContainer();
        return kc;
    }


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

    }
}
