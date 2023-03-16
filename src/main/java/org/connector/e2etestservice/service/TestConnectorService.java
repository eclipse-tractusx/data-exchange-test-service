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

import lombok.extern.slf4j.Slf4j;
import org.connector.e2etestservice.api.DataOfferCreationProxy;
import org.connector.e2etestservice.api.QueryDataOffersProxy;
import org.connector.e2etestservice.model.ConnectorTestRequest;
import org.connector.e2etestservice.model.asset.AssetFactory;
import org.connector.e2etestservice.model.contractDefinition.ContractDefinitionFactory;
import org.connector.e2etestservice.model.contractoffers.ContractOffer;
import org.connector.e2etestservice.model.contractoffers.ContractOffersCatalogResponse;
import org.connector.e2etestservice.model.policies.PolicyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TestConnectorService {

    private String testConnectorUrl;
    private String testConnectorApiKeyHeader;
    private String testConnectorApiKey;

    private ConnectorUtility connectorUtility;

    private QueryDataOffersProxy queryDataOffersProxy;

    private DataOfferCreationProxy dataOfferCreationProxy;
    private DataOfferService dataOfferService;

    @Autowired
    public TestConnectorService(@Value("${default.edc.hostname}") String testConnectorUrl,
                                @Value("${default.edc.apiKeyHeader}") String testConnectorApiKeyHeader,
                                @Value("${default.edc.apiKey}") String testConnectorApiKey,
                                ConnectorUtility connectorUtility,
                                QueryDataOffersProxy queryDataOffersProxy,
                                DataOfferCreationProxy dataOfferCreationProxy,
                                DataOfferService dataOfferService) {
        this.testConnectorUrl = testConnectorUrl;
        this.testConnectorApiKeyHeader = testConnectorApiKeyHeader;
        this.testConnectorApiKey = testConnectorApiKey;
        this.connectorUtility = connectorUtility;
        this.queryDataOffersProxy = queryDataOffersProxy;
        this.dataOfferCreationProxy = dataOfferCreationProxy;
        this.dataOfferService = dataOfferService;
    }

    public boolean testConnectorAsConsumer(ConnectorTestRequest companyConnectorRequest) {
        try {
            ConnectorTestRequest preconfiguredTestConnector = ConnectorTestRequest.builder()
                    .connectorHost(testConnectorUrl)
                    .apiKeyHeader(testConnectorApiKeyHeader)
                    .apiKeyValue(testConnectorApiKey)
                    .build();

            ContractOffersCatalogResponse contractOfferCatalog = connectorUtility.getContractOfferFromConnector(
                    companyConnectorRequest,
                    companyConnectorRequest.getApiKeyHeader(),
                    companyConnectorRequest.getApiKeyValue(),
                    preconfiguredTestConnector
            );

            if (contractOfferCatalog != null) {
                contractOfferCatalog.getContractOffers()
                        .stream().forEach(
                                contractOffer -> {
                                    log.info(contractOffer.getAsset().getProperties().get("asset:prop:id"));
                                    log.info(contractOffer.getAsset().getProperties().get("asset:prop:description"));
                                }
                        );
            } else {
                return false;
            }
        } catch (Exception e) {
            log.info("Exception occurred" + e);
            return false;
        }
        return true;
    }


    public boolean testConnectorAsProvider(ConnectorTestRequest companyConnectorRequest) {
        ConnectorTestRequest preconfiguredTestConnector = ConnectorTestRequest.builder()
                .connectorHost(testConnectorUrl)
                .apiKeyHeader(testConnectorApiKeyHeader)
                .apiKeyValue(testConnectorApiKey)
                .build();


        // 1. Check if the data offer is already present
        ContractOffersCatalogResponse contractOfferCatalog = connectorUtility.getContractOfferFromConnector(
                companyConnectorRequest,
                companyConnectorRequest.getApiKeyHeader(),
                companyConnectorRequest.getApiKeyValue(),
                companyConnectorRequest
        );

        List listOfOffers = contractOfferCatalog.getContractOffers()
                .stream()
                .filter(contractOffer
                        -> contractOffer.getAsset().getProperties().get("asset:prop:id")
                        .equalsIgnoreCase("200")).collect(Collectors.toList());

        if(listOfOffers == null || listOfOffers.isEmpty()) {
            // If data offer is not already present then create new one
            dataOfferService.createDataOfferForTesting(companyConnectorRequest);
        } else {
            log.info("Data offer is already present. No need to create new dummy data offer");
        }


        try {
            // 3. Fetch newly created data offer from preconfigured test connector
            contractOfferCatalog = connectorUtility.getContractOfferFromConnector(
                    preconfiguredTestConnector,
                    preconfiguredTestConnector.getApiKeyHeader(),
                    preconfiguredTestConnector.getApiKeyValue(),
                    companyConnectorRequest
            );

            if(!contractOfferCatalog.getContractOffers().isEmpty()) {
                contractOfferCatalog.getContractOffers().stream()
                        .forEach(contractOffer -> {
                            log.info(contractOffer.getAsset().getProperties().get("asset:prop:id"));
                            log.info(contractOffer.getAsset().getProperties().get("asset:prop:description"));
                            log.info("-----------\n");
                        });
                return true;
            }
        } catch (Exception e) {
            log.info("Exception occurred"+ e);
            return false;
        }
        return true;
    }

}
