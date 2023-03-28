package org.connector.e2etestservice.service;

import org.connector.e2etestservice.facilitator.ConnectorFacilitator;
import org.connector.e2etestservice.model.ConnectorTestRequest;
import org.connector.e2etestservice.model.asset.AssetEntryRequest;
import org.connector.e2etestservice.model.asset.AssetFactory;
import org.connector.e2etestservice.model.contractDefinition.ContractDefinitionFactory;
import org.connector.e2etestservice.model.contractDefinition.ContractDefinitionRequest;
import org.connector.e2etestservice.model.policies.PolicyDefinitionRequest;
import org.connector.e2etestservice.model.policies.PolicyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DataOfferService {
	private AssetFactory assetFactory;
	private PolicyFactory policyFactory;
	private ContractDefinitionFactory contractDefinitionFactory;
	private ConnectorFacilitator connectorFacilitator;

	@Autowired
	public DataOfferService(AssetFactory assetFactory, PolicyFactory policyFactory,
			ContractDefinitionFactory contractDefinitionFactory, ConnectorFacilitator connectorFacilitator) {
		this.assetFactory = assetFactory;
		this.policyFactory = policyFactory;
		this.contractDefinitionFactory = contractDefinitionFactory;
		this.connectorFacilitator = connectorFacilitator;
	}

	public void createDataOfferForTesting(ConnectorTestRequest companyConnectorRequest) {
		try {
			// 1. check if already present and then Create Asset
			if (!connectorFacilitator.isTestAssetPresent(companyConnectorRequest)) {
				AssetEntryRequest assetEntryRequest = assetFactory.generateDummyAssetObejct();
				connectorFacilitator.createAsset(companyConnectorRequest, assetEntryRequest);
			}

			// 2. check if already present and then Create policies
			if (!connectorFacilitator.isTestPolicyPresent(companyConnectorRequest, "AccessPolicy200")) {
				PolicyDefinitionRequest accessPolicy = policyFactory
						.generateDummyAccessPolicyRequest("AccessPolicy200");
				connectorFacilitator.createPolicy(companyConnectorRequest, accessPolicy);
			}

			if (!connectorFacilitator.isTestPolicyPresent(companyConnectorRequest, "UsagePolicy200")) {
				PolicyDefinitionRequest usagePolicy = policyFactory.generateDummyUsagePolicyRequest("UsagePolicy200");
				connectorFacilitator.createPolicy(companyConnectorRequest, usagePolicy);
			}

			// 3. check if already present and then Create contract definition
			if (!connectorFacilitator.isTestContractDefinitionPresent(companyConnectorRequest)) {
				ContractDefinitionRequest contractDefinitionRequest = contractDefinitionFactory
						.getDummyContractDefinitionRequest("ContractDefinition200", "AccessPolicy200", "UsagePolicy200",
								"200");
				connectorFacilitator.createContractDefinition(companyConnectorRequest, contractDefinitionRequest);
			}

		} catch (Exception e) {
			log.info("Exception occurred while creating test data offer for connector: " + e);
		}

	}
}
