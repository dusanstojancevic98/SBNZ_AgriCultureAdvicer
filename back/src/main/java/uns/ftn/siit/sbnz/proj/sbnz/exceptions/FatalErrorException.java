package uns.ftn.siit.sbnz.proj.sbnz.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class FatalErrorException extends RuntimeException{

    private static final long serialVersionUID = 5L;

    public FatalErrorException(String message) {
        super(message);
    }
}
