package nl.ordina.recruitmentexperience.app;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomRestErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ IllegalStateException.class})
    public ResponseEntity handleAccessDeniedException() {
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}