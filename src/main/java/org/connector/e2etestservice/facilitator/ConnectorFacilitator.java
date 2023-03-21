package org.connector.e2etestservice.facilitator;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.connector.e2etestservice.api.DataOfferCreationProxy;
import org.connector.e2etestservice.api.QueryDataOffersProxy;
import org.connector.e2etestservice.model.ConnectorTestRequest;
import org.connector.e2etestservice.model.asset.AssetEntryRequest;
import org.connector.e2etestservice.model.contractDefinition.ContractDefinitionRequest;
import org.connector.e2etestservice.model.contractoffers.ContractOffersCatalogResponse;
import org.connector.e2etestservice.model.policies.PolicyDefinitionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
@Slf4j
public class ConnectorFacilitator extends AbstractEDCStepsHelper {

    private QueryDataOffersProxy queryDataOffersProxy;

    private DataOfferCreationProxy dataOfferCreationProxy;

    private String idsPath;

    @Autowired
    public ConnectorFacilitator(QueryDataOffersProxy queryDataOffersProxy,
                                DataOfferCreationProxy dataOfferCreationProxy,
                                @Value("${edc.ids.path}") String idsPath) {
        this.queryDataOffersProxy = queryDataOffersProxy;
        this.dataOfferCreationProxy = dataOfferCreationProxy;
        this.idsPath = idsPath;
    }


    @SneakyThrows
    public ContractOffersCatalogResponse getContractOfferFromConnector(ConnectorTestRequest consumer,
                                                                       ConnectorTestRequest provider) {
        ContractOffersCatalogResponse contractOfferCatalog = null;
        URI companyConnectorURI = URI.create(consumer.getConnectorHost());
        contractOfferCatalog = queryDataOffersProxy.getContractOffersCatalog(
                companyConnectorURI,
                getAuthHeader(consumer.getApiKeyHeader(), consumer.getApiKeyValue()),
                provider.getConnectorHost() + idsPath,
                10
        );
        return contractOfferCatalog;
    }

    @SneakyThrows
    public ResponseEntity<String> createAsset(ConnectorTestRequest connectorRequest,
                                              AssetEntryRequest assetEntryRequest) {
        ResponseEntity<String> response = null;
        URI connectorURI = URI.create(connectorRequest.getConnectorHost());
        response = dataOfferCreationProxy.createAsset(
                connectorURI,
                getAuthHeader(connectorRequest.getApiKeyHeader(), connectorRequest.getApiKeyValue()),
                assetEntryRequest
        );
        return response;
    }


    public boolean isTestAssetPresent(ConnectorTestRequest connectorRequest) {
        try {
            URI connectorURI = URI.create(connectorRequest.getConnectorHost());
            ResponseEntity<String> response = dataOfferCreationProxy.checkIfAssetIsPresent(
                    connectorURI,
                    getAuthHeader(connectorRequest.getApiKeyHeader(), connectorRequest.getApiKeyValue()),
                    "200"
            );
            if (response.getStatusCodeValue() == 200) {
                log.info("Test Asset Present");
                return true;
            } else {
                log.info("Test Asset not present");
                return false;
            }
        } catch (Exception e) {
            log.info("Test Asset not present");
            return false;
        }
    }

    @SneakyThrows
    public ResponseEntity<String> createPolicy(ConnectorTestRequest connectorRequest,
                                              PolicyDefinitionRequest policyDefinitionRequest) {
        ResponseEntity<String> response = null;
        URI connectorURI = URI.create(connectorRequest.getConnectorHost());
        response = dataOfferCreationProxy.createPolicy(
                connectorURI,
                getAuthHeader(connectorRequest.getApiKeyHeader(), connectorRequest.getApiKeyValue()),
                policyDefinitionRequest
        );
        return response;
    }

    @SneakyThrows
    public boolean isTestPolicyPresent(ConnectorTestRequest connectorRequest, String policyId) {
        try {
            URI connectorURI = URI.create(connectorRequest.getConnectorHost());
            ResponseEntity<String> response = dataOfferCreationProxy.checkIfPolicyIsPresent(
                    connectorURI,
                    getAuthHeader(connectorRequest.getApiKeyHeader(), connectorRequest.getApiKeyValue()),
                    policyId
            );
            if (response.getStatusCodeValue() == 200) {
                log.info("Test policy Present: " + policyId);
                return true;
            } else {
                log.info("Test policy not present:" + policyId);
                return false;
            }
        } catch (Exception e) {
                log.info("Test policy not present:"+ policyId);
            return false;
        }
    }

    @SneakyThrows
    public ResponseEntity<String> createContractDefinition(ConnectorTestRequest connectorRequest,
                                               ContractDefinitionRequest contractDefinitionRequest) {
        ResponseEntity<String> response = null;
        URI connectorURI = URI.create(connectorRequest.getConnectorHost());
        response = dataOfferCreationProxy.createContractDefinition(
                connectorURI,
                getAuthHeader(connectorRequest.getApiKeyHeader(), connectorRequest.getApiKeyValue()),
                contractDefinitionRequest
        );
        return response;
    }

    @SneakyThrows
    public boolean isTestContractDefinitionPresent(ConnectorTestRequest connectorRequest) {
        try {
            URI connectorURI = URI.create(connectorRequest.getConnectorHost());
            ResponseEntity<String> response = dataOfferCreationProxy.checkIfContractDefinitionIsPresent(
                    connectorURI,
                    getAuthHeader(connectorRequest.getApiKeyHeader(), connectorRequest.getApiKeyValue()),
                    "ContractDefinition200"
            );
            if(response.getStatusCodeValue() == 200) {
                log.info("Test contract definition is Present");
                return true;
            } else {
                log.info("Test contract definition is not present");
                return false;
            }
        } catch (Exception e) {
            log.info("Test contract definition is not present");
            return false;
        }
    }
}
