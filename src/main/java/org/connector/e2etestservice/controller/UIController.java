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

package org.connector.e2etestservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UIController {

    private static final String RESULT = "result";

    private String testConnectorUrl;
    private String testConnectorApiKeyHeader;
    private String testConnectorApiKey;

    @Autowired
    public UIController(@Value("${default.edc.hostname}") String testConnectorUrl,
                        @Value("${default.edc.apiKeyHeader}") String testConnectorApiKeyHeader,
                        @Value("${default.edc.apiKey}") String testConnectorApiKey) {
        this.testConnectorUrl = testConnectorUrl;
        this.testConnectorApiKeyHeader = testConnectorApiKeyHeader;
        this.testConnectorApiKey = testConnectorApiKey;
    }


    @GetMapping(value = "/")
    public ModelAndView getIndexPage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("index");
        model.addObject("preconfiguredConnectorUrl",testConnectorUrl);
        model.addObject("connectorApiKeyHeader","X-Api-Key");
        return model;
    }
}
