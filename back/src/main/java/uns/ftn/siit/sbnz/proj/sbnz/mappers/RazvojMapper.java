package uns.ftn.siit.sbnz.proj.sbnz.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uns.ftn.siit.sbnz.proj.sbnz.dto.AkcijaResponse;
import uns.ftn.siit.sbnz.proj.sbnz.dto.RazvojRequest;
import uns.ftn.siit.sbnz.proj.sbnz.dto.RazvojResponse;
import uns.ftn.siit.sbnz.proj.sbnz.model.Razvoj;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RazvojMapper extends Mapper<Razvoj, RazvojResponse, RazvojRequest> {

    private AkcijaMapper akcijaMapper;

    @Autowired
    public RazvojMapper(AkcijaMapper akcijaMapper) {
        this.akcijaMapper = akcijaMapper;
    }

    @Override
    public RazvojResponse toResponse(Razvoj razvoj) {
        List<AkcijaResponse> istorija = razvoj.getIstorijaAkcija().stream().map(akcija -> akcijaMapper.toResponse(akcija)).collect(Collectors.toList());
        List<AkcijaResponse> trenutne = razvoj.getTrenutnaAkcija().stream().map(akcija -> akcijaMapper.toResponse(akcija)).collect(Collectors.toList());
        return new RazvojResponse(
                razvoj.getId(),
                razvoj.getNaziv(),
                razvoj.getStanjeRazvoja(),
                razvoj.getPonudaUseva(),
                razvoj.getOdabraniUsev(),
                razvoj.getBudzet(),
                razvoj.getPocetakSadjenja(),
                istorija,
                trenutne,
                razvoj.getKonfiguracija()
        );
    }

    @Override
    public Razvoj fromRequest(RazvojRequest dto) {
        return null;
    }

    @Override
    public Razvoj fromResponse(RazvojResponse dto) {
        return null;
    }
}
