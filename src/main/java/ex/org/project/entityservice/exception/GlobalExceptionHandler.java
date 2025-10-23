package ex.org.project.entityservice.exception;


import ex.org.project.datahub.auth.exception.UserAuthenticationException;
import ex.org.project.datahub.auth.exception.UserAuthorizationException;
import ex.org.project.datahub.auth.exception.UserNotFoundException;
import ex.org.project.entityservice.exception.custom.StatusNotFoundException;
import ex.org.project.entityservice.exception.custom.StudyNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponseDTO> resourceNotFoundException(ResourceNotFoundException e) {
        log.warn("Resource Not Found", e);
        HttpStatus status = HttpStatus.NOT_FOUND;
        ExceptionResponseDTO responseDTO = new ExceptionResponseDTO(
                "Resource Not Found",
                status.value(),
                e.getMessage()
        );
        return new ResponseEntity<>(responseDTO, status);
    }

    @ExceptionHandler(RuntimeException.class)
    ResponseEntity<ExceptionResponseDTO> runtimeException(RuntimeException e){
        log.error(e.getMessage(), e);
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ExceptionResponseDTO responseDTO = new ExceptionResponseDTO(
                "Internal Server Error",
                status.value(),
                "An unknown error has occurred. Please contact support if the issue persists."
        );
        return new ResponseEntity<>(responseDTO, status);
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<ExceptionResponseDTO> exception(Exception e){
        log.error(e.getMessage(), e);
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ExceptionResponseDTO responseDTO = new ExceptionResponseDTO(
                "Internal Server Error",
                status.value(),
                "An unknown error has occurred. Please contact support if the issue persists."
        );
        return new ResponseEntity<>(responseDTO, status);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public final ResponseEntity<ExceptionResponseDTO> methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e){
        log.warn(e.getMessage(), e);
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ExceptionResponseDTO responseDTO = new ExceptionResponseDTO(
                "Invalid Id",
                status.value(),
                "Malformed Id found in the request."
        );
        return new ResponseEntity<>(responseDTO, status);
    }

    @ExceptionHandler(MalformedRequestException.class)
    public final ResponseEntity<ExceptionResponseDTO> malformedRequestException(MalformedRequestException e){
        log.warn(e.getMessage(), e);
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ExceptionResponseDTO responseDTO = new ExceptionResponseDTO(
                "Invalid parameter",
                status.value(),
                "Malformed parameter found in the request."
        );
        return new ResponseEntity<>(responseDTO, status);
    }

    @ExceptionHandler(UserAuthorizationException.class)
    ResponseEntity<ExceptionResponseDTO> authorizationException(UserAuthorizationException e){
        HttpStatus status = HttpStatus.FORBIDDEN;
        ExceptionResponseDTO responseDTO = new ExceptionResponseDTO(
                "Unauthorized",
                status.value(),
                e.getMessage()
        );
        return new ResponseEntity<>(responseDTO, status);
    }

    @ExceptionHandler(UserAuthenticationException.class)
    ResponseEntity<ExceptionResponseDTO> authenticationException(UserAuthenticationException e){
        //why in the world does the "unauthorized" exception actually mean unauthenticated
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        ExceptionResponseDTO responseDTO = new ExceptionResponseDTO(
                "Unauthenticated",
                status.value(),
                e.getMessage()
        );
        return new ResponseEntity<>(responseDTO, status);
    }

    @ExceptionHandler(UserNotFoundException.class)
    ResponseEntity<ExceptionResponseDTO> authenticationException(UserNotFoundException e){
        HttpStatus status = HttpStatus.NOT_FOUND;
        ExceptionResponseDTO responseDTO = new ExceptionResponseDTO(
                "User Not Found",
                status.value(),
                e.getMessage()
        );
        return new ResponseEntity<>(responseDTO, status);
    }

    @ExceptionHandler(StatusNotFoundException.class)
    ResponseEntity<ExceptionResponseDTO> statusNotFoundException(StatusNotFoundException e){
        log.warn("Invalid Status Value", e);
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ExceptionResponseDTO responseDTO = new ExceptionResponseDTO(
                "Invalid Status Value",
                status.value(),
                e.getMessage()
        );
        return new ResponseEntity<>(responseDTO, status);
    }

    @ExceptionHandler(StudyNotFoundException.class)
    ResponseEntity<ExceptionResponseDTO> studyNotFoundException(StudyNotFoundException e){
        HttpStatus status = HttpStatus.NOT_FOUND;
        ExceptionResponseDTO responseDTO = new ExceptionResponseDTO(
                "Study Not Found",
                status.value(),
                e.getMessage()
        );
        return new ResponseEntity<>(responseDTO, status);
    }
}
