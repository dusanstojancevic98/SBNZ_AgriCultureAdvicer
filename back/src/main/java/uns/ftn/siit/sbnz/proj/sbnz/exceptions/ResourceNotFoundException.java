package uns.ftn.siit.sbnz.proj.sbnz.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ResourceNotFoundException extends RuntimeException{


	public ResourceNotFoundException(String message) {
		super(message);
	}
}
