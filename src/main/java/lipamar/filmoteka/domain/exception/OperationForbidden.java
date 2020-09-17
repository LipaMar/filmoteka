package lipamar.filmoteka.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN,reason = "No permission")
public class OperationForbidden extends RuntimeException {
    public OperationForbidden() {
        super();
    }

    public OperationForbidden(String message) {
        super(message);
    }
}
