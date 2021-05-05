package uns.ftn.siit.sbnz.proj.sbnz.service;


import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uns.ftn.siit.sbnz.proj.sbnz.model.*;

@Service
public class OdabirUsevaService {

    @Autowired
    private KieContainer kieContainer;


    public Usev odaberiUsev(Konfiguracija konfiguracija, Razvoj razvoj){
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.setGlobal("razvoj", razvoj);
//        kieSession.setGlobal("vremenkiPodaci", vremenskiPodaci);
//        kieSession.insert(konfiguracija);
        kieSession.insert(konfiguracija);
//        kieSession.insert(razvoj);
        kieSession.fireAllRules();
        kieSession.dispose();
        return razvoj.getOdabraniUsev();

    }
}
