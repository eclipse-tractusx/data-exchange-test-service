package org.connector.e2etestservice.api;

import org.connector.e2etestservice.model.AssetCreationRequest;
import org.connector.e2etestservice.model.contractoffers.ContractOffersCatalogResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URI;
import java.util.Map;

@FeignClient(name = "QueryData", url = "placeholder")
public interface QueryDataOffersProxy {

    @GetMapping(value = "/data/catalog")
    public ContractOffersCatalogResponse getContractOffersCatalog(
            URI baseUrl,
            @RequestHeader Map<String, String> requestHeader,
            @RequestParam String providerUrl,
            @RequestParam Integer limit
    );
}
