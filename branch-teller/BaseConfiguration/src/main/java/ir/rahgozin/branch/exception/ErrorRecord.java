package ir.rahgozin.branch.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ErrorRecord(
        int httpStatus,
        String errorCode,
        String errorEnumString,
        String message,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        LocalDateTime dateTime,
        String path,
        String params
) {
}
