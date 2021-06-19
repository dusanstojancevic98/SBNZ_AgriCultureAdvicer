package uns.ftn.siit.sbnz.proj.sbnz.service;


import com.mysql.cj.QueryResult;
import lombok.Getter;
import lombok.Setter;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.time.SessionClock;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.kie.api.time.SessionPseudoClock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import uns.ftn.siit.sbnz.proj.sbnz.model.*;
import uns.ftn.siit.sbnz.proj.sbnz.repository.RazvojRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Getter
@Setter
public class OdabirUsevaService {
    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private RazvojRepository razvojRepo;

    private KieSession kieSession;

    public List<StanjeUseva> getStanja(Long idRazvoja){

        Razvoj razvoj = razvojRepo.findById(idRazvoja).get();
        return razvoj.getStanjaUseva();
    }

    public Razvoj preporuciUseve(Razvoj razvoj){
        kieSession = applicationContext.getBean("basic",KieSession.class);
        kieSession.insert(razvoj);
        kieSession.insert(razvoj.getKonfiguracija());
        int fired = kieSession.fireAllRules();
        System.out.println("FIRED" + fired);
        return razvoj;

    }

    public List<Akcija> dodajStanje(StanjeUseva podaci){
        QueryResults result = kieSession.getQueryResults("getRazvoj");
        Razvoj razvoj = (Razvoj) result.iterator().next().get("$razvoj");
        razvojRepo.save(razvoj);
        razvoj.getStanjaUseva().add(podaci);
        kieSession.insert(razvoj);
        int fired = kieSession.fireAllRules();
        System.out.println("FIRED: " + fired);
        razvojRepo.save(razvoj);


        return razvoj.getTrenutnaAkcija();

    }
}
