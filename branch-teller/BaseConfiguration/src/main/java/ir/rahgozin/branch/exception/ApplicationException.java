package ir.rahgozin.branch.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApplicationException extends RuntimeException{
    private String errorCode;
    private String errorEnumString;
    private HttpStatus httpStatus;
    private String[] params;

    public ApplicationException(ErrorCodeEnumInterface errorCode, String... params) {
        this.errorCode = errorCode.getErrorCode();
        this.errorEnumString = errorCode.getErrorEnumString();
        this.httpStatus = errorCode.getHttpStatus();
        this.params = params;
    }

}
