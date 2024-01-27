package ir.rahgozin.branch.externalservice.hostadapter.client;

import ir.rahgozin.branch.externalservice.hostadapter.model.response.HostAdapterGetAllCurrencyResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import static ir.rahgozin.branch.externalservice.hostadapter.common.ConstantValues.AUTHORIZATION;

@FeignClient(value = "hostAdapterClient", url = "${endpoint.hostadapter-url}")
public interface HostAdapterClient {
    @GetMapping(value = "/api/v1/get-all-currency", produces = "application/json")
    ResponseEntity<HostAdapterGetAllCurrencyResponse> getAllCurrency(@RequestHeader(value = AUTHORIZATION) String auth);

}
