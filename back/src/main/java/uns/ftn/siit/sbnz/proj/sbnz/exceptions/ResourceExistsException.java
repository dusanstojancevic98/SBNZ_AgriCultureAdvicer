package uns.ftn.siit.sbnz.proj.sbnz.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ResourceExistsException extends RuntimeException{


	public ResourceExistsException(String message) {
		super(message);
	}
}
