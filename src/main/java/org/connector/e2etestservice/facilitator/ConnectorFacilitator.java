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

package org.connector.e2etestservice.facilitator;

import java.net.URI;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.connector.e2etestservice.api.DataOfferCreationProxy;
import org.connector.e2etestservice.api.QueryDataOffersProxy;
import org.connector.e2etestservice.model.ConnectorTestRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ConnectorFacilitator extends AbstractEDCStepsHelper {

    private QueryDataOffersProxy queryDataOffersProxy;

    private DataOfferCreationProxy dataOfferCreationProxy;

    @Autowired
    public ConnectorFacilitator(QueryDataOffersProxy queryDataOffersProxy,
                                DataOfferCreationProxy dataOfferCreationProxy) {
        this.queryDataOffersProxy = queryDataOffersProxy;
        this.dataOfferCreationProxy = dataOfferCreationProxy;
    }


    @SneakyThrows
    public ResponseEntity<String> getContractOfferFromConnector(ConnectorTestRequest consumer,
                                                                       ObjectNode catalogRequestBody) {

        URI companyConnectorURI = URI.create(consumer.getConnectorHost());
        return queryDataOffersProxy.getContractOffersCatalog(
                companyConnectorURI,
                getAuthHeader(consumer.getApiKeyHeader(), consumer.getApiKeyValue()),
                catalogRequestBody
        );
    }

    @SneakyThrows
    public ResponseEntity<String> createAsset(ConnectorTestRequest connectorRequest,
                                              ObjectNode assetEntryRequest) {
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
                    "sample"
            );
            if (response.getStatusCode().value() == 200) {
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
                                              ObjectNode policyDefinitionRequest) {
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
            if (response.getStatusCode().value() == 200) {
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
                                                           ObjectNode contractDefinitionRequest) {
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
                    "samplecontract"
            );
            if(response.getStatusCode().value() == 200) {
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
