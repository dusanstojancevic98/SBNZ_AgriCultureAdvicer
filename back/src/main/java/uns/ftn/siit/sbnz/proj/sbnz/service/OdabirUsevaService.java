package uns.ftn.siit.sbnz.proj.sbnz.service;


import lombok.Getter;
import lombok.Setter;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.time.SessionPseudoClock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uns.ftn.siit.sbnz.proj.sbnz.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Getter
@Setter
public class OdabirUsevaService {
    @Autowired
    private KieSession kieSession;

    public Razvoj preporuciUseve(Razvoj razvoj){
        kieSession.insert(razvoj);
        kieSession.insert(razvoj.getKonfiguracija());
        int fired = kieSession.fireAllRules();
        System.out.println("FIRED" + fired);
        return razvoj;

    }

    public void ubrzajVreme(Integer time){
        SessionPseudoClock clock = kieSession.getSessionClock();
        System.out.println(clock.advanceTime(time, TimeUnit.DAYS));
    }

    public List<Akcija> dodajStanje(Razvoj razvoj, UsevPodaci podaci){
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
