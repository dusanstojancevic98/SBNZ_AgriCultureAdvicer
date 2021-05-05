package uns.ftn.siit.sbnz.proj.sbnz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import uns.ftn.siit.sbnz.proj.sbnz.model.*;
import uns.ftn.siit.sbnz.proj.sbnz.service.OdabirUsevaService;

@SpringBootApplication
public class SbnzApplication {

    @Autowired
    static OdabirUsevaService odabirUsevaService;

    public static void main(String[] args) {
        SpringApplication.run(SbnzApplication.class, args);
        }

}
