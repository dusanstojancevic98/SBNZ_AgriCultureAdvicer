package uns.ftn.siit.sbnz.proj.sbnz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.runner.RunWith;
import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import uns.ftn.siit.sbnz.proj.sbnz.model.*;
import uns.ftn.siit.sbnz.proj.sbnz.model.enums.TipPadavine;

import java.util.ArrayList;

@SpringBootTest(classes= SbnzApplication.class)
@RunWith(SpringRunner.class)
class SbnzApplicationTests {

    @Autowired
    private KieContainer kieContainer;

    static private KieSession kieSession;

    static UsevPodaci podaci;
    static Razvoj razvoj;

    @BeforeAll
    static void loadContext(){
        razvoj = new Razvoj(new Korisnik());
        razvoj.setTrenutnaAkcija(new ArrayList<>());

        podaci = new UsevPodaci();
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
    }


    @Test
    void testProtivGradnaMreza (){
        VremenskaPrognoza vp = new VremenskaPrognoza();
        vp.setTipPadavine(TipPadavine.GRAD);

        razvoj.setTrenutnaAkcija(new ArrayList<>());

        kieSession = kieContainer.newKieSession("cepConfigKsessionPseudoClock");
        kieSession.insert(razvoj);
        kieSession.insert(podaci);
        kieSession.insert(vp);
        int fired = kieSession.fireAllRules();
        assertEquals(1, fired);
        assertEquals(razvoj.getTrenutnaAkcija().get(0).getNaziv(), "Aktiviraj mrezu");
        kieSession.dispose();
    }

    @Test
    void testPrskanjeProtivTrulezi(){
        kieSession = kieContainer.newKieSession("cepConfigKsessionPseudoClock");
        for (int i =0; i < 10; i++){

            VremenskaPrognoza vp = new VremenskaPrognoza();
            vp.setTipPadavine(TipPadavine.KISA);
            vp.setKolicinaPadavina(40 + i);
            vp.setJacinaVetra(2 + i);

            kieSession.insert(vp);
        }


        razvoj.setTrenutnaAkcija(new ArrayList<>());

        kieSession.insert(razvoj);
        kieSession.insert(podaci);
        int fired = kieSession.fireAllRules();
        assertEquals(1, fired);
        assertEquals("Prskanje", razvoj.getTrenutnaAkcija().get(0).getNaziv());
        kieSession.dispose();
    }

    @Test
    void testPrskanjeProtivTruleziBezPrskanja(){
        kieSession = kieContainer.newKieSession("cepConfigKsessionPseudoClock");
        for (int i =0; i < 10; i++){

            VremenskaPrognoza vp = new VremenskaPrognoza();
            vp.setTipPadavine(TipPadavine.KISA);
            vp.setKolicinaPadavina(40 + i);
            vp.setJacinaVetra(2 + i);

            kieSession.insert(vp);
        }


        razvoj.setTrenutnaAkcija(new ArrayList<>());
        kieSession.insert(new Akcija("Prskanje", "Neko Prskanje"));
        kieSession.insert(razvoj);
        kieSession.insert(podaci);
        int fired = kieSession.fireAllRules();
        assertEquals(1, fired);
        assertEquals("Rucna zastita", razvoj.getTrenutnaAkcija().get(0).getNaziv());
        kieSession.dispose();
    }


    @Test
    void testOdbranaOdJakogSnega(){
        kieSession = kieContainer.newKieSession("cepConfigKsessionPseudoClock");
        for (int i =0; i < 4; i++){

            VremenskaPrognoza vp = new VremenskaPrognoza();
            vp.setTipPadavine(TipPadavine.SNEG);
            vp.setKolicinaPadavina(40 + i);
            vp.setJacinaVetra(6 + i);

            kieSession.insert(vp);
        }

        VremenskaPrognoza vp = new VremenskaPrognoza();
        vp.setKolicinaPadavina(0);
        kieSession.insert(vp);

        razvoj.setTrenutnaAkcija(new ArrayList<>());
        kieSession.insert(razvoj);
        kieSession.insert(podaci);
        int fired = kieSession.fireAllRules();
        assertEquals(1, fired);
        assertEquals("Potrebno je breskvu zastiti prepartom", razvoj.getTrenutnaAkcija().get(0).getOpisAkcije());
        kieSession.dispose();
    }

    @Test
    void testOdbranaOdSusnogMraza(){
        kieSession = kieContainer.newKieSession("cepConfigKsessionPseudoClock");
        for (int i =0; i < 4; i++){

            VremenskaPrognoza vp = new VremenskaPrognoza();
            vp.setMaximalnaTemperatura(-2);
            vp.setKolicinaPadavina(40 + i);
            vp.setJacinaVetra(6 + i);

            kieSession.insert(vp);
        }

        VremenskaPrognoza vp = new VremenskaPrognoza();
        vp.setKolicinaPadavina(0);
        kieSession.insert(vp);

        razvoj.setTrenutnaAkcija(new ArrayList<>());
        kieSession.insert(razvoj);
        kieSession.insert(podaci);
        int fired = kieSession.fireAllRules();
        assertEquals(1, fired);
        assertEquals("Potrebno je oko biljaka zaloziti vatru", razvoj.getTrenutnaAkcija().get(0).getOpisAkcije());
        kieSession.dispose();
    }

    @Test
    void testPrevencijaMraza(){
        kieSession = kieContainer.newKieSession("cepConfigKsessionPseudoClock");
        for (int i =0; i < 7; i++){

            VremenskaPrognoza vp = new VremenskaPrognoza();
            vp.setTipPadavine(TipPadavine.SNEG);
            vp.setKolicinaPadavina(40 + i);
            vp.setJacinaVetra(6 + i);

            kieSession.getEntryPoint("predvidjena-prognoza").insert(vp);
        }

        VremenskaPrognoza vp = new VremenskaPrognoza();
        vp.setKolicinaPadavina(0);
        kieSession.insert(vp);

        razvoj.setTrenutnaAkcija(new ArrayList<>());
        kieSession.insert(razvoj);
        kieSession.insert(podaci);
        int fired = kieSession.fireAllRules();
        assertEquals(1, fired);
        assertEquals("Potrebno je isprskati biljku hemijskim preparatom protiv mraza", razvoj.getTrenutnaAkcija().get(0).getOpisAkcije());
        kieSession.dispose();
    }

    @Test
    void testBezPrevencijaMraza(){
        kieSession = kieContainer.newKieSession("cepConfigKsessionPseudoClock");
        for (int i =0; i < 7; i++){

            VremenskaPrognoza vp = new VremenskaPrognoza();
            vp.setTipPadavine(TipPadavine.SNEG);
            vp.setKolicinaPadavina(40 + i);
            vp.setJacinaVetra(6 + i);

            kieSession.getEntryPoint("predvidjena-prognoza").insert(vp);
        }

        VremenskaPrognoza vp = new VremenskaPrognoza();
        vp.setKolicinaPadavina(0);
        kieSession.insert(new Akcija("Prevencija snega", "SDDDD"));
        kieSession.insert(vp);

        razvoj.setTrenutnaAkcija(new ArrayList<>());
        kieSession.insert(razvoj);
        kieSession.insert(podaci);
        int fired = kieSession.fireAllRules();
        assertEquals(0, fired);
        kieSession.dispose();
    }
}
