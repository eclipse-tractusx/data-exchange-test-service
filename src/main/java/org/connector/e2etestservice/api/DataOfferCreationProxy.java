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

package org.connector.e2etestservice.api;

import org.connector.e2etestservice.model.asset.AssetEntryRequest;
import org.connector.e2etestservice.model.contractDefinition.ContractDefinitionRequest;
import org.connector.e2etestservice.model.policies.PolicyDefinitionRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.net.URI;
import java.util.Map;

@FeignClient(name = "DataOfferCreation", url = "placeholder")
public interface DataOfferCreationProxy {
    @PostMapping(value = "/data/assets")
    public ResponseEntity<String> createAsset(
            URI baseUrl,
            @RequestHeader Map<String, String> requestHeader,
            @RequestBody AssetEntryRequest assetEntryRequest
    );

    @GetMapping(value = "/data/assets/{assetId}")
    public ResponseEntity<String> checkIfAssetIsPresent(
            URI baseUrl,
            @RequestHeader Map<String, String> requestHeader,
            @PathVariable String assetId
    );

    @PostMapping(value = "/data/policydefinitions")
    public ResponseEntity<String> createPolicy(
            URI baseUrl,
            @RequestHeader Map<String, String> requestHeader,
            @RequestBody PolicyDefinitionRequest policyDefinitionRequest
    );

    @GetMapping(value = "/data/policydefinitions/{policyId}")
    public ResponseEntity<String> checkIfPolicyIsPresent(
            URI baseUrl,
            @RequestHeader Map<String, String> requestHeader,
            @PathVariable String policyId
    );

    @PostMapping(value = "/data/contractdefinitions")
    public ResponseEntity<String> createContractDefinition(
            URI baseUrl,
            @RequestHeader Map<String, String> requestHeader,
            @RequestBody ContractDefinitionRequest contractDefinitionRequest
    );

    @GetMapping(value = "/data/contractdefinitions/{contractDefinitionsId}")
    public ResponseEntity<String> checkIfContractDefinitionIsPresent(
            URI baseUrl,
            @RequestHeader Map<String, String> requestHeader,
            @PathVariable String contractDefinitionsId
    );

}
