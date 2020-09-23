package lipamar.filmoteka.domain.system;

import lipamar.filmoteka.domain.exception.OperationForbidden;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(OperationForbidden.class)
    public String forbidden(){
        return "error/forbidden";
    }
}
