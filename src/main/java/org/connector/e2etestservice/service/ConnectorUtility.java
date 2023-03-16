package org.connector.e2etestservice.service;

import lombok.extern.slf4j.Slf4j;
import org.connector.e2etestservice.api.DataOfferCreationProxy;
import org.connector.e2etestservice.api.QueryDataOffersProxy;
import org.connector.e2etestservice.facilitator.AbstractEDCStepsHelper;
import org.connector.e2etestservice.model.ConnectorTestRequest;
import org.connector.e2etestservice.model.asset.AssetEntryRequest;
import org.connector.e2etestservice.model.contractDefinition.ContractDefinitionRequest;
import org.connector.e2etestservice.model.contractoffers.ContractOffersCatalogResponse;
import org.connector.e2etestservice.model.policies.PolicyDefinitionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
@Slf4j
public class ConnectorUtility extends AbstractEDCStepsHelper {

    private QueryDataOffersProxy queryDataOffersProxy;

    private DataOfferCreationProxy dataOfferCreationProxy;

    @Autowired
    public ConnectorUtility(QueryDataOffersProxy queryDataOffersProxy,
                            DataOfferCreationProxy dataOfferCreationProxy) {
        this.queryDataOffersProxy = queryDataOffersProxy;
        this.dataOfferCreationProxy = dataOfferCreationProxy;
    }


    public ContractOffersCatalogResponse getContractOfferFromConnector(ConnectorTestRequest consumer,
                                                                       String consumerApiKeyHeader,
                                                                       String consumerApiKey,
                                                                       ConnectorTestRequest provider) {
        ContractOffersCatalogResponse contractOfferCatalog = null;
        try {
            URI companyConnectorURI = URI.create(consumer.getConnectorHost());
            contractOfferCatalog = queryDataOffersProxy.getContractOffersCatalog(
                    companyConnectorURI,
                    getAuthHeader(consumerApiKeyHeader, consumerApiKey),
                    provider.getConnectorHost() + "/api/v1/ids/data",
                    10
            );
        } catch (Exception e) {
            log.info("Exception occurred" + e);
            return null;
        }
        return contractOfferCatalog;
    }

    public ResponseEntity<String> createAsset(ConnectorTestRequest connectorRequest,
                                              AssetEntryRequest assetEntryRequest) {
        ResponseEntity<String> response = null;
        try {
            URI connectorURI = URI.create(connectorRequest.getConnectorHost());
            response = dataOfferCreationProxy.createAsset(
                    connectorURI,
                    getAuthHeader(connectorRequest.getApiKeyHeader(), connectorRequest.getApiKeyValue()),
                    assetEntryRequest
            );
        } catch (Exception e) {
            log.info("Exception occurred" + e);
            return null;
        }
        return response;
    }

    public ResponseEntity<String> createPolicy(ConnectorTestRequest connectorRequest,
                                              PolicyDefinitionRequest policyDefinitionRequest) {
        ResponseEntity<String> response = null;
        try {
            URI connectorURI = URI.create(connectorRequest.getConnectorHost());
            response = dataOfferCreationProxy.createPolicy(
                    connectorURI,
                    getAuthHeader(connectorRequest.getApiKeyHeader(), connectorRequest.getApiKeyValue()),
                    policyDefinitionRequest
            );
        } catch (Exception e) {
            log.info("Exception occurred" + e);
            return null;
        }
        return response;
    }

    public ResponseEntity<String> createContractDefinition(ConnectorTestRequest connectorRequest,
                                               ContractDefinitionRequest contractDefinitionRequest) {
        ResponseEntity<String> response = null;
        try {
            URI connectorURI = URI.create(connectorRequest.getConnectorHost());
            response = dataOfferCreationProxy.createContractDefinition(
                    connectorURI,
                    getAuthHeader(connectorRequest.getApiKeyHeader(), connectorRequest.getApiKeyValue()),
                    contractDefinitionRequest
            );
        } catch (Exception e) {
            log.info("Exception occurred" + e);
            return null;
        }
        return response;
    }
}
