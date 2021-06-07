package uns.ftn.siit.sbnz.proj.sbnz.mappers;

public interface Mapper<E, RESPONSE, REQUEST>{
    RESPONSE toResponse(E entity);
    E fromRequest(REQUEST dto);
    E fromResponse(RESPONSE dto);
}
