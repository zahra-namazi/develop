package ir.rahgozin.branch.exception;

import org.springframework.context.MessageSource;
import org.springframework.util.StringUtils;

import java.util.Locale;

public class ErrorMessageHelper {
    public static String getErrorMessage(MessageSource messageSource, String errorEnumString, String...params) {
        try {
            if (StringUtils.hasText(errorEnumString)) {
                Locale currentLocale = Locale.getDefault();
                return messageSource.getMessage(errorEnumString, params, currentLocale);
            }
        }catch (Exception ex) {
            return errorEnumString;
        }
        return errorEnumString;
    }
}
