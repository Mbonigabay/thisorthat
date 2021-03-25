package rw.thisorthat.thisorthat.exception;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends Throwable {

    /**
     *
     */
    private static final long serialVersionUID = -8155584913420296377L;

    @ExceptionHandler(ResourceAlreadyExistException.class)
    public ResponseEntity<?> ResourceAlreadyExistException(ResourceAlreadyExistException ex) {
        HttpStatus status = HttpStatus.CONFLICT;
        ErrorDetails errorDetails = new ErrorDetails(ZonedDateTime.now(ZoneId.of("Z")), ex.getMessage(), ex,
                HttpStatus.CONFLICT);
        return new ResponseEntity<>(errorDetails, status);
    }
   
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> ResourceNotFoundException(ResourceNotFoundException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorDetails errorDetails = new ErrorDetails(ZonedDateTime.now(ZoneId.of("Z")), ex.getMessage(), ex,
                HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorDetails, status);
    }
}
