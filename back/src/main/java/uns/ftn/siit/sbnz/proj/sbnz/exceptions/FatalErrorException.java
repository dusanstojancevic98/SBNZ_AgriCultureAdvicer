package uns.ftn.siit.sbnz.proj.sbnz.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class FatalErrorException extends RuntimeException{


    public FatalErrorException(String message) {
        super(message);
    }
}
