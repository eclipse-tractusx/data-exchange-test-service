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

package org.connector.e2etestservice.model.asset;

import org.connector.e2etestservice.constants.EDCAssetConstant;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AssetFactory {


    public AssetEntryRequest generateDummyAssetObejct() {

        HashMap<String, String> assetProperties = new HashMap<>();
        assetProperties.put(EDCAssetConstant.ASSET_PROP_ID, "200");
        assetProperties.put(EDCAssetConstant.ASSET_PROP_DESCRIPTION, "Test data offer");

        HashMap<String, String> dataAddressProperties = new HashMap<>();
        dataAddressProperties.put("type", "HttpData");
        dataAddressProperties.put("baseUrl", "https://jsonplaceholder.typicode.com/todos/3");

        return AssetEntryRequest.builder()
                .asset(Asset.builder()
                        .properties(assetProperties)
                        .build())
                .dataAddress(DataAddressRequest.builder()
                        .properties(dataAddressProperties)
                        .build())
                .build();
    }
}
