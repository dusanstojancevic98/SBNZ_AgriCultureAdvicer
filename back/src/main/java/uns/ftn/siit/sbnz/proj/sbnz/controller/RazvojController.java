package uns.ftn.siit.sbnz.proj.sbnz.controller;

import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uns.ftn.siit.sbnz.proj.sbnz.dto.RazvojResponse;
import uns.ftn.siit.sbnz.proj.sbnz.model.Razvoj;
import uns.ftn.siit.sbnz.proj.sbnz.service.RazvojService;

import java.util.List;


@RestController
@RequestMapping(value = "/api/razvoj", produces = MediaType.APPLICATION_JSON_VALUE)
public class RazvojController {

    private final RazvojService razvojService;

    @Autowired
    public RazvojController(RazvojService razvojService) {
        this.razvojService = razvojService;
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Razvoj>> getRazvoji(){
        return new ResponseEntity<>(razvojService.getAll(), HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> addRazvoj(@RequestBody Razvoj razvoj)  {
        return new ResponseEntity<>(razvojService.addOne(razvoj), HttpStatus.CREATED);
    }
}
