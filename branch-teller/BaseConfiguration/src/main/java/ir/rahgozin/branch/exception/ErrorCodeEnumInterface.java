package ir.rahgozin.branch.exception;

import org.springframework.http.HttpStatus;

public interface ErrorCodeEnumInterface {
    String getErrorCode();
    String getErrorEnumString();
    HttpStatus getHttpStatus();
}
