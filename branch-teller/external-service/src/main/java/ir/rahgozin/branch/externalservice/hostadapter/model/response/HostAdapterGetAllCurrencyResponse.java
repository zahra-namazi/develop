package ir.rahgozin.branch.externalservice.hostadapter.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class HostAdapterGetAllCurrencyResponse implements Serializable {
    private List<CurrencyDto> currencyDtoList;
}
