package uns.ftn.siit.sbnz.proj.sbnz.mappers;

import java.util.List;
import java.util.stream.Collectors;

public abstract class Mapper<E, RESPONSE, REQUEST>{
    abstract RESPONSE toResponse(E entity);
    abstract E fromRequest(REQUEST dto);
    abstract E fromResponse(RESPONSE dto);

    public List<RESPONSE> fromEntityList(List<E> eList){
        return eList.stream().map(this::toResponse).collect(Collectors.toList());
    }
}
