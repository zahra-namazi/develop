package ir.rahgozin.branch.user.enumeration;

import ir.rahgozin.branch.exception.ErrorCodeEnumInterface;
import org.springframework.http.HttpStatus;

public enum UserErrorCodeEnum implements ErrorCodeEnumInterface {
    USER_ALREADY_EXISTS("01-0001", HttpStatus.BAD_REQUEST);

    UserErrorCodeEnum(String errorCode, HttpStatus httpStatus) {
        this.errorCode = errorCode;
        this.httpStatus =  httpStatus;
    }
    private String errorCode;
    private HttpStatus httpStatus;

    @Override
    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getErrorEnumString() {
        return this.name();
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
