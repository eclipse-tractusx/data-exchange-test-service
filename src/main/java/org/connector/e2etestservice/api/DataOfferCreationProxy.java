package org.connector.e2etestservice.api;

import feign.Response;
import org.connector.e2etestservice.model.asset.AssetEntryRequest;
import org.connector.e2etestservice.model.contractDefinition.ContractDefinitionRequest;
import org.connector.e2etestservice.model.contractoffers.ContractOffersCatalogResponse;
import org.connector.e2etestservice.model.policies.PolicyDefinitionRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URI;
import java.util.Map;

@FeignClient(name = "DataOfferCreation", url = "placeholder")
public interface DataOfferCreationProxy {
    @PostMapping(value = "/data/assets")
    public ResponseEntity<String> createAsset(
            URI baseUrl,
            @RequestHeader Map<String, String> requestHeader,
            @RequestBody AssetEntryRequest assetEntryRequest
    );

    @PostMapping(value = "/data/policydefinitions")
    public ResponseEntity<String> createPolicy(
            URI baseUrl,
            @RequestHeader Map<String, String> requestHeader,
            @RequestBody PolicyDefinitionRequest policyDefinitionRequest
    );

    @PostMapping(value = "/data/contractdefinitions")
    public ResponseEntity<String> createContractDefinition(
            URI baseUrl,
            @RequestHeader Map<String, String> requestHeader,
            @RequestBody ContractDefinitionRequest contractDefinitionRequest
    );

}
