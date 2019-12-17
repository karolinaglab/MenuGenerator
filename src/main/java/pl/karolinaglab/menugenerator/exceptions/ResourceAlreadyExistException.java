package pl.karolinaglab.menugenerator.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ResourceAlreadyExistException extends Exception{

    public ResourceAlreadyExistException(String message) {
        super(message);
    }
}
