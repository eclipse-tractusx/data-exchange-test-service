/********************************************************************************
 * Copyright (c) 2023 T-Systems International GmbH
 * Copyright (c) 2023 Contributors to the Eclipse Foundation
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Apache License, Version 2.0 which is available at
 * https://www.apache.org/licenses/LICENSE-2.0.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 *
 * SPDX-License-Identifier: Apache-2.0
 ********************************************************************************/

package org.connector.e2etestservice.service;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.connector.e2etestservice.facilitator.ConnectorFacilitator;
import org.connector.e2etestservice.model.ConnectorTestRequest;
import org.connector.e2etestservice.model.EdcTemplateFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DataOfferService {
	private EdcTemplateFactory edcTemplateFactory;
	private ConnectorFacilitator connectorFacilitator;
	private String sampleAssetName;

	@Autowired
	public DataOfferService(EdcTemplateFactory edcTemplateFactory,
							ConnectorFacilitator connectorFacilitator,
							@Value("${sample.asset.name}") String sampleAssetName) {
		this.edcTemplateFactory = edcTemplateFactory;
		this.connectorFacilitator = connectorFacilitator;
		this.sampleAssetName = sampleAssetName;
	}

	public void createDataOfferForTesting(ConnectorTestRequest companyConnectorRequest) {
		try {
			// 1. check if already present and then Create Asset
			if (!connectorFacilitator.isTestAssetPresent(companyConnectorRequest)) {
				ObjectNode assetEntryRequest = edcTemplateFactory.
						generateDynamicDummyEdcRequestObject("/edc-request-template/sample-asset.json", sampleAssetName);
				connectorFacilitator.createAsset(companyConnectorRequest, assetEntryRequest);
			}

			// 2. check if already present and then Create policies
			if (!connectorFacilitator.isTestPolicyPresent(companyConnectorRequest, "policysample")) {
				ObjectNode policyRequest = edcTemplateFactory.
						generateDummyEdcRequestObject("/edc-request-template/sample-policy.json");
				connectorFacilitator.createPolicy(companyConnectorRequest, policyRequest);
			}

			// 3. check if already present and then Create contract definition
			if (!connectorFacilitator.isTestContractDefinitionPresent(companyConnectorRequest)) {
				ObjectNode contractDefinitionRequest = edcTemplateFactory.
						generateDummyEdcRequestObject("/edc-request-template/sample-contract-definition.json");
				connectorFacilitator.createContractDefinition(companyConnectorRequest, contractDefinitionRequest);
			}

		} catch (Exception e) {
			log.info("Exception occurred while creating test data offer for connector: " + e);
		}

	}

	public ObjectNode getCatalogRequestBody(String providerProtocolUrl) {
		return edcTemplateFactory.
				generateDynamicDummyEdcRequestObject(
						"/edc-request-template/sample-catalog.json", providerProtocolUrl);
	}
}
