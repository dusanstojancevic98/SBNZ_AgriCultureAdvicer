package uns.ftn.siit.sbnz.proj.sbnz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uns.ftn.siit.sbnz.proj.sbnz.service.RazvojService;

@RestController
@RequestMapping(value = "/api/akcija", produces = MediaType.APPLICATION_JSON_VALUE)
public class AkcijaController {

    private final RazvojService razvojService;

    @Autowired
    public AkcijaController(RazvojService razvojService) {
        this.razvojService = razvojService;
    }


    @GetMapping("/odradi/{id}/{razvojId}")
    public void odradiAkciju(@PathVariable Long id, @PathVariable Long razvojId){
        System.out.println("KO JE OVDE LUD");
        razvojService.odradiAkciju(id, razvojId);
    }

    @GetMapping("/odbaci/{id}/{razvojId}")
    public void odbaciAkciju(@PathVariable Long id, @PathVariable Long razvojId){
        razvojService.odbaciAkciju(id,razvojId);
    }

}
