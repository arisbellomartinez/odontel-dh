package clinicaaris.ontontel;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalException {
    private static final Logger logger=Logger.getLogger(GlobalException.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> allError(Exception e, WebRequest request){
        logger.error(e.getMessage());
        return new ResponseEntity("Error exepcional: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
