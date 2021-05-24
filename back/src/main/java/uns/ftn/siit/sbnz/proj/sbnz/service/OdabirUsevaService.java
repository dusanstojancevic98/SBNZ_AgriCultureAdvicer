package uns.ftn.siit.sbnz.proj.sbnz.service;


import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uns.ftn.siit.sbnz.proj.sbnz.model.*;

import java.util.List;

@Service
public class OdabirUsevaService {

    @Autowired
    private KieContainer kieContainer;


    public List<Usev> preporuciUseve(Konfiguracija konfiguracija, Razvoj razvoj){
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.setGlobal("razvoj", razvoj);
//        kieSession.setGlobal("vremenkiPodaci", vremenskiPodaci);
//        kieSession.insert(konfiguracija);
        kieSession.insert(konfiguracija);
//        kieSession.insert(razvoj);
        int fired = kieSession.fireAllRules();
        System.out.println("FIRED" + fired);
        kieSession.dispose();
        return razvoj.getPonudaUseva().getPonude();

    }
}
