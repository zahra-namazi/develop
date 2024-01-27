package ir.rahgozin.branch.externalservice.hostadapter.service;

import ir.rahgozin.branch.externalservice.hostadapter.model.response.HostAdapterGetAllCurrencyResponse;
import org.springframework.http.ResponseEntity;

public interface HostAdapterService {
    ResponseEntity<HostAdapterGetAllCurrencyResponse> getAllCurrency();
}