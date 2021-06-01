package uns.ftn.siit.sbnz.proj.sbnz.service;


import lombok.Getter;
import lombok.Setter;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uns.ftn.siit.sbnz.proj.sbnz.model.*;

import java.util.ArrayList;
import java.util.List;

@Service
@Getter
@Setter
public class OdabirUsevaService {

    @Autowired
    private KieContainer kieContainer;

    private KieSession kieSession;

    public List<Usev> preporuciUseve(Konfiguracija konfiguracija, Razvoj razvoj){

        kieSession = kieContainer.newKieSession("cepConfigKsessionPseudoClock");
        kieSession.insert(razvoj);
        kieSession.insert(konfiguracija);
        int fired = kieSession.fireAllRules();
        System.out.println("FIRED" + fired);
        return razvoj.getPonudaUseva().getPonude();

    }

    public List<Akcija> dodajStanje(Razvoj razvoj, UsevPodaci podaci){

        kieSession = kieContainer.newKieSession();
        List<Uslov> uslovi = new ArrayList<>();

        uslovi.add(Uslov.ZutiList);
        uslovi.add(Uslov.PrisustvoPalamide);

        UsloviAkcija uslov = new UsloviAkcija(new Akcija("prskaj", "neko prskanje"), uslovi, 2  );
        kieSession.insert(uslov);
        kieSession.insert(razvoj);
        kieSession.insert(podaci);
        int fired = kieSession.fireAllRules();
        System.out.println("FIRED" + fired);
        return razvoj.getTrenutnaAkcija();

    }
}
