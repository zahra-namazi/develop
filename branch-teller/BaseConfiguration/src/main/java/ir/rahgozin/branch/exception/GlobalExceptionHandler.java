package ir.rahgozin.branch.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    public GlobalExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    private static final String INPUT_VALIDATION_ERROR_CODE = "00-1111";

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorRecord> handleValidationExceptions(MethodArgumentNotValidException ex, HttpServletRequest request) {
        String errorEnumString =  ex.getBindingResult()
                .getAllErrors()
                .get(0)
                .getDefaultMessage()
                .replace("{","")
                .replace("}", "");


        String[] params = null;
        ErrorRecord error = ErrorRecord.builder()
                .path(request.getRequestURI())
                .httpStatus(HttpStatus.BAD_REQUEST.value())
                .errorCode(INPUT_VALIDATION_ERROR_CODE)
                .errorEnumString(errorEnumString)
                .message(ErrorMessageHelper.getErrorMessage(messageSource, errorEnumString, params))
                .params(params != null ? String.join(", ", params) : "")
                .dateTime(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}