package org.connector.e2etestservice.service;

import lombok.extern.slf4j.Slf4j;
import org.connector.e2etestservice.model.ConnectorTestRequest;
import org.connector.e2etestservice.model.asset.AssetEntryRequest;
import org.connector.e2etestservice.model.asset.AssetFactory;
import org.connector.e2etestservice.model.contractDefinition.ContractDefinitionFactory;
import org.connector.e2etestservice.model.contractDefinition.ContractDefinitionRequest;
import org.connector.e2etestservice.model.policies.PolicyDefinitionRequest;
import org.connector.e2etestservice.model.policies.PolicyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DataOfferService {
    private AssetFactory assetFactory;
    private PolicyFactory policyFactory;
    private ContractDefinitionFactory contractDefinitionFactory;
    private ConnectorUtility connectorUtility;

    @Autowired
    public DataOfferService(AssetFactory assetFactory,
                            PolicyFactory policyFactory,
                            ContractDefinitionFactory contractDefinitionFactory, ConnectorUtility connectorUtility) {
        this.assetFactory = assetFactory;
        this.policyFactory = policyFactory;
        this.contractDefinitionFactory = contractDefinitionFactory;
        this.connectorUtility = connectorUtility;
    }


    public void createDataOfferForTesting(ConnectorTestRequest companyConnectorRequest) {
        try {
            // 1. Create Asset
            AssetEntryRequest assetEntryRequest = assetFactory.generateDummyAssetObejct();
            ResponseEntity<String> assetCreationResponse = connectorUtility.createAsset(
                    companyConnectorRequest,
                    assetEntryRequest);

            log.info("Response code for asset creation: " + assetCreationResponse.getStatusCode());

            // 2. Create policies
            PolicyDefinitionRequest accessPolicy = policyFactory.generateDummyAccessPolicyRequest("AccessPolicy200");
            ResponseEntity<String> accessPolicyCreationResponse = connectorUtility.createPolicy(
                    companyConnectorRequest,
                    accessPolicy);

            PolicyDefinitionRequest usagePolicy = policyFactory.generateDummyUsagePolicyRequest("UsagePolicy200");
            ResponseEntity<String> usagePolicyCreationResponse = connectorUtility.createPolicy(
                    companyConnectorRequest,
                    usagePolicy);

            log.info("Response code for access policy creation: " + accessPolicyCreationResponse.getStatusCode());
            log.info("Response code for usage policy creation: " + usagePolicyCreationResponse.getStatusCode());

            // 3. Create contract definition
            ContractDefinitionRequest contractDefinitionRequest =
                    contractDefinitionFactory.getDummyContractDefinitionRequest("ContractDefinition200",
                            "AccessPolicy200",
                            "UsagePolicy199",
                            "200");
            ResponseEntity<String> contractDefinitionResponse = connectorUtility.createContractDefinition(
                    companyConnectorRequest,
                    contractDefinitionRequest);

            log.info("Response code for contract Definition creation: " + contractDefinitionResponse.getStatusCode());
        } catch (Exception e) {
            log.info("Exception occured "+e);
        }

    }
}
