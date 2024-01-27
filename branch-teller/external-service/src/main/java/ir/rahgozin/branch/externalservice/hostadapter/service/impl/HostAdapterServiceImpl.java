package ir.rahgozin.branch.externalservice.hostadapter.service.impl;

import feign.FeignException;
import ir.rahgozin.branch.externalservice.hostadapter.client.HostAdapterClient;
import ir.rahgozin.branch.externalservice.hostadapter.model.response.HostAdapterGetAllCurrencyResponse;
import ir.rahgozin.branch.externalservice.hostadapter.service.HostAdapterService;
import ir.rahgozin.branch.externalservice.iam.service.IamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HostAdapterServiceImpl implements HostAdapterService {
    private final HostAdapterClient hostAdapterClient;
    private final IamService iamService;

    @Override
    public ResponseEntity<HostAdapterGetAllCurrencyResponse> getAllCurrency() {
        try {
            return hostAdapterClient.getAllCurrency(iamService.getToken());
        } catch (FeignException e) {
            //TODO throw exception
            return null;
        }
    }

}
