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

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.connector.e2etestservice.model.ConnectorTestRequest;
import org.connector.e2etestservice.model.OwnConnectorTestRequest;
import org.connector.e2etestservice.service.TestConnectorService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class E2eTestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TestConnectorService mockTestConnectorService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    @Test
    void testConnectorMethod_WithValidDto() {
        Mockito.when(mockTestConnectorService.testConnectorConnectivity(any(), any()))
                        .thenReturn(true);

        mvc.perform(MockMvcRequestBuilders
                .post("/connector-test")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(getValidConnectorTestRequest()))
        ).andExpect(status().isOk());

    }

    @SneakyThrows
    @Test
    void testConnectorMethod_WithInvalidDto() {
        Mockito.when(mockTestConnectorService.testConnectorConnectivity(any(), any()))
                .thenReturn(true);

        mvc.perform(MockMvcRequestBuilders
                .post("/connector-test")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(getInvalidConnectorTestRequest()))
        ).andExpect(status().is(HttpStatus.BAD_REQUEST.value()));

    }

    @SneakyThrows
    @Test
    void testOwnConnectorMethod_WithValidDto() {
        Mockito.when(mockTestConnectorService.testConnectorConnectivity(any(), any()))
                .thenReturn(true);

        mvc.perform(MockMvcRequestBuilders
                .post("/own-connector-test")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(getValidOwnConnectorTestRequest()))
        ).andExpect(status().isOk());

    }

    @SneakyThrows
    @Test
    void testOwnConnectorMethod_WithInvalidDto() {
        Mockito.when(mockTestConnectorService.testConnectorConnectivity(any(), any()))
                .thenReturn(true);

        mvc.perform(MockMvcRequestBuilders
                .post("/connector-test")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(getInvalidOwnConnectorTestRequest()))
        ).andExpect(status().is(HttpStatus.BAD_REQUEST.value()));

    }

    private ConnectorTestRequest getValidConnectorTestRequest() {
        return ConnectorTestRequest.builder()
                .apiKeyValue("api_key")
                .apiKeyHeader("api_key_header")
                .connectorHost("https://connector_host.com")
                .connectorId("connector_id")
                .build();
    }

    private ConnectorTestRequest getInvalidConnectorTestRequest() {
        return ConnectorTestRequest.builder()
                .build();
    }

    private OwnConnectorTestRequest getValidOwnConnectorTestRequest() {
        return OwnConnectorTestRequest.builder()
                .firstConnectorHost("https://connector_host.com")
                .firstConnectorId("connector_id")
                .firstApiKeyHeader("api_key_header")
                .firstApiKeyValue("api_key")
                .secondConnectorHost("https://connector_host.com")
                .secondConnectorId("connector_id")
                .secondApiKeyHeader("api_key_header")
                .secondApiKeyValue("api_key")
                .build();
    }

    private OwnConnectorTestRequest getInvalidOwnConnectorTestRequest() {
        return OwnConnectorTestRequest.builder()
                .build();
    }
}
