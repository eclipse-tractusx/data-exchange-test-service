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

package org.connector.e2etestservice.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ConnectorTestRequest {

    @NotNull(message = "Connector host is mandatory")
    private String connectorHost;

    @NotNull(message = "Api key header is mandatory")
    private String apiKeyHeader;

    @NotNull(message = "Api key is mandatory")
    private String apiKeyValue;

}
