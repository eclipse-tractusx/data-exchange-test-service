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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.connector.e2etestservice.facilitator.ConnectorFacilitator;
import org.connector.e2etestservice.model.ConnectorTestRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TestConnectorService {

	private ConnectorFacilitator connectorFacilitator;

	private DataOfferService dataOfferService;

	@Autowired
	public TestConnectorService(ConnectorFacilitator connectorFacilitator, DataOfferService dataOfferService) {
		this.connectorFacilitator = connectorFacilitator;
		this.dataOfferService = dataOfferService;
	}

	public boolean testConnectorConnectivity(ConnectorTestRequest consumerTestConnector,
			ConnectorTestRequest providerConnectorRequest) {
		try {
			// 1. Create data offer for provider
			dataOfferService.createDataOfferForTesting(providerConnectorRequest);

			// 2. Fetch newly created data offer from preconfigured test connector
			ObjectNode catalogRequestBody = dataOfferService.getCatalogRequestBody(
					providerConnectorRequest.getConnectorHost()+"/api/v1/dsp"
			);
			ResponseEntity<String> contractOfferCatalog = connectorFacilitator
					.getContractOfferFromConnector(consumerTestConnector, catalogRequestBody);

			ObjectNode response = (ObjectNode) new ObjectMapper().readTree(contractOfferCatalog.getBody());

			return response != null && !response.get("dcat:dataset").isEmpty();

		} catch (Exception e) {
			log.info("Exception occurred while testing connector as a provider" + e);
			return false;
		}
	}

}
