package squareonex.evetracker.controllers.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidJobException extends RuntimeException{
    public InvalidJobException() {
        super();
    }

    public InvalidJobException(String message) {
        super(message);
    }

    public InvalidJobException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidJobException(Throwable cause) {
        super(cause);
    }

    protected InvalidJobException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
