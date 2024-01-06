package ir.rahgozin.branch.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.Locale;

@ControllerAdvice
public class ApplicationExceptionHandler {

    private final MessageSource messageSource;

    public ApplicationExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErrorRecord> handleException(ApplicationException e, HttpServletRequest request) {
        ErrorRecord error = ErrorRecord.builder()
                .path(request.getRequestURI())
                .httpStatus(e.getHttpStatus().value())
                .errorCode(e.getErrorCode())
                .errorEnumString(e.getErrorEnumString())
                .message(ErrorMessageHelper.getErrorMessage(messageSource, e.getErrorEnumString(), e.getParams()))
                .params(e.getParams() != null ? String.join(", ", e.getParams()): "")
                .dateTime(LocalDateTime.now())
        .build();

        return new ResponseEntity<>(error, e.getHttpStatus());
    }
}
