/********************************************************************************
 * Copyright (c) 2023,2024 T-Systems International GmbH
 * Copyright (c) 2023,2024 Contributors to the Eclipse Foundation
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

import org.connector.e2etestservice.facilitator.ConnectorFacilitator;
import org.connector.e2etestservice.model.ConnectorTestRequest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class ConnectorServiceTest {

    @Autowired
    private TestConnectorService testConnectorService;

    @MockBean
    private ConnectorFacilitator connectorFacilitator;

    @MockBean
    private DataOfferService dataOfferService;

    @Test
    public void connectorTestingMethod_Success() {
        ConnectorTestRequest consumerConnector = getValidConnectorTestRequest();
        ConnectorTestRequest providerConnector = getValidConnectorTestRequest();

        Mockito.when(dataOfferService.getCatalogRequestBody(providerConnector.getConnectorHost()+"/api/v1/dsp"))
                .thenReturn(any());
        Mockito.when(connectorFacilitator.getContractOfferFromConnector(consumerConnector, any()))
                .thenReturn(getResponseEntityFromConnector());

        assertTrue(testConnectorService.testConnectorConnectivity(consumerConnector,
                providerConnector));
    }

    private ConnectorTestRequest getValidConnectorTestRequest() {
        return ConnectorTestRequest.builder()
                .apiKeyValue("api_key")
                .apiKeyHeader("api_key_header")
                .connectorHost("https://connector_host.com")
                .build();
    }

    private ResponseEntity<String> getResponseEntityFromConnector() {
        String connectorRes = """
                {
                    "@id": "f9050d7a",
                    "@type": "dcat:Catalog",
                    "dcat:dataset": {
                        "@id": "sample",
                        "@type": "dcat:Dataset"
                    }
                }""";

        return new ResponseEntity<>(connectorRes, HttpStatus.OK);
    }

}
