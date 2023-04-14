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

package org.connector.e2etestservice.model.policies;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class PolicyFactory {

    public PolicyDefinitionRequest generateDummyAccessPolicyRequest(String accessPolicyId) {

        HashMap<String, String> type = new HashMap<>();
        type.put("@policytype","set");

        PolicyRequest policyRequest = PolicyRequest.builder()
                .permissions(getPermissionsForAccessPolicy())
                .prohibitions(new ArrayList<>())
                .obligations(new ArrayList<>())
                .assigner("")
                .assignee("")
                .type(type)
                .build();
        return PolicyDefinitionRequest.builder()
                .id(accessPolicyId)
                .policyRequest(policyRequest)
                .build();
    }

    public PolicyDefinitionRequest generateDummyUsagePolicyRequest(String usagePolicyId) {

        HashMap<String, String> type = new HashMap<>();
        type.put("@policytype","set");

        PolicyRequest policyRequest = PolicyRequest.builder()
                .permissions(getPermissionsForUsagePolicy())
                .prohibitions(new ArrayList<>())
                .obligations(new ArrayList<>())
                .assigner("")
                .assignee("")
                .type(type)
                .build();
        return PolicyDefinitionRequest.builder()
                .id(usagePolicyId)
                .policyRequest(policyRequest)
                .build();
    }

    public List<PermissionRequest> getPermissionsForAccessPolicy() {
        ArrayList<PermissionRequest> permissions = new ArrayList<>();
        Action action = Action.builder()
                .type("USE")
                .build();

        ArrayList<Constraint> constraintList = new ArrayList<>();
        Expression leftExpression = Expression.builder()
                .edcType("dataspaceconnector:literalexpression")
                .value("idsc:ELAPSED_TIME")
                .build();

        Expression rightExpression = Expression.builder()
                .edcType("dataspaceconnector:literalexpression")
                .value("P0Y0M3DT0H0M0S")
                .build();

        Constraint constraint = Constraint.builder()
                .edcType("AtomicConstraint")
                .operator("LEQ")
                .leftExpression(leftExpression)
                .rightExpression(rightExpression)
                .build();
        constraintList.add(constraint);

        PermissionRequest permissionRequest = PermissionRequest.builder()
                .action(action)
                .constraints(constraintList)
                .edcType("dataspaceconnector:permission")
                .build();
        permissions.add(permissionRequest);
        return permissions;
    }

    public List<PermissionRequest> getPermissionsForUsagePolicy() {
        ArrayList<PermissionRequest> permissions = new ArrayList<>();
        Action action = Action.builder()
                .type("USE")
                .build();

        PermissionRequest permissionRequest = PermissionRequest.builder()
                .action(action)
                .edcType("dataspaceconnector:permission")
                .build();
        permissions.add(permissionRequest);
        return permissions;
    }
}
