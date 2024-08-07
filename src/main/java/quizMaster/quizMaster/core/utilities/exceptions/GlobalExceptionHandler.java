package quizMaster.quizMaster.core.utilities.exceptions;

import com.nimbusds.jose.JOSEException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.io.IOException;
import java.text.ParseException;

@ControllerAdvice
public class GlobalExceptionHandler {
    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest request) {
        return new ResponseEntity<>(5000, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        return new ResponseEntity<>(5001,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception ex) {
        logger.error(ex.getMessage());
        return new ResponseEntity<>(5002,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(JOSEException.class)
    public ResponseEntity<?> handleJOSEException(JOSEException ex) {
        logger.error(ex.getMessage());
        return new ResponseEntity<>(5003, HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(IOException.class)
    public ResponseEntity<?> handleIOException(IOException ex) {
        logger.error(ex.getMessage());
        return new ResponseEntity<>(5004, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(ParseException.class)
    public ResponseEntity<?> handleParseException(ParseException ex) {
        logger.error(ex.getMessage());
        return new ResponseEntity<>(5005, HttpStatus.BAD_REQUEST);
    }
}
