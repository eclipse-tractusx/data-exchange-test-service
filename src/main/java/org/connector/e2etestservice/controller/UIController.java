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

package org.connector.e2etestservice.controller;

import jakarta.validation.constraints.NotBlank;
import org.connector.e2etestservice.Utils;
import org.connector.e2etestservice.model.ConnectorTestRequest;
import org.connector.e2etestservice.service.TestConnectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UIController {

    private static final String RESULT = "result";

    private String testConnectorUrl;
    private String testConnectorApiKeyHeader;
    private String testConnectorApiKey;

    private TestConnectorService testConnectorService;

    @Autowired
    public UIController(@Value("${default.edc.hostname}") String testConnectorUrl,
                        @Value("${default.edc.apiKeyHeader}") String testConnectorApiKeyHeader,
                        @Value("${default.edc.apiKey}") String testConnectorApiKey, TestConnectorService testConnectorService) {
        this.testConnectorUrl = testConnectorUrl;
        this.testConnectorApiKeyHeader = testConnectorApiKeyHeader;
        this.testConnectorApiKey = testConnectorApiKey;
        this.testConnectorService = testConnectorService;
    }


    @GetMapping(value = "/")
    public ModelAndView getIndexPage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("index");
        model.addObject("preconfiguredConnectorUrl",testConnectorUrl);
        model.addObject("connectorApiKeyHeader","X-Api-Key");
        return model;
    }

    @PostMapping(value = "/testconnector")
    public ModelAndView getIndexPageWithConnectorDetails(@NotBlank @RequestParam String connectorHost,
                                                         @NotBlank @RequestParam String apiKeyHeader,
                                                         @NotBlank @RequestParam String apiKeyValue) {

        ConnectorTestRequest connectorTestRequest = ConnectorTestRequest.builder()
                .connectorHost(connectorHost)
                .apiKeyHeader(apiKeyHeader)
                .apiKeyValue(apiKeyValue)
                .build();
        ModelAndView model = new ModelAndView();
        model.setViewName("index");

        connectorTestRequest.setConnectorHost(Utils.removeLastSlashFromURL(connectorTestRequest.getConnectorHost()));

        ConnectorTestRequest preconfiguredTestConnector = ConnectorTestRequest.builder()
                .connectorHost(testConnectorUrl)
                .apiKeyHeader(testConnectorApiKeyHeader).apiKeyValue(testConnectorApiKey).build();
        boolean consumerTestResult = testConnectorService.testConnectorConnectivity(connectorTestRequest,
                preconfiguredTestConnector);
        boolean providerTestResult = testConnectorService.testConnectorConnectivity(preconfiguredTestConnector,
                connectorTestRequest);
        if (consumerTestResult && providerTestResult) {
            model.addObject(RESULT, "Connector is working as a consumer and provider");
        } else {
            model.addObject(RESULT, "Connector is not working properly");
        }

        model.addObject("preconfiguredConnectorUrl",testConnectorUrl);
        model.addObject("connectorUrl",connectorTestRequest.getConnectorHost());
        model.addObject("connectorApiKeyHeader",connectorTestRequest.getApiKeyHeader());
        model.addObject("connectorApiKey",connectorTestRequest.getApiKeyValue());
        return model;
    }
}
