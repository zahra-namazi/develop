package ir.rahgozin.branch.externalservice.hostadapter.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class CurrencyDto implements Serializable {
    private String code;
    private String name;
}