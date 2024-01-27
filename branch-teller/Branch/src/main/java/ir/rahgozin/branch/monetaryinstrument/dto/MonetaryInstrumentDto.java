package ir.rahgozin.branch.monetaryinstrument.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MonetaryInstrumentDto {
    @NotNull
    private String code;
    @NotNull
    private String currency;
    @NotNull
    private Boolean isCash;
    private String transitAccount;
    private Boolean isDeleted = false;
}


