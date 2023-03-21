package org.connector.e2etestservice.model.contractDefinition;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContractDefinitionFactory {

    public ContractDefinitionRequest getDummyContractDefinitionRequest(String id,
                                                                       String accessPolicyId,
                                                                       String usagePolicyId,
                                                                       String assetId) {
        List<Criterion> criteriaList = new ArrayList<>();
        Criterion criterion = Criterion.builder()
                .operandLeft("asset:prop:id")
                .operator("=")
                .operandRight(assetId)
                .build();
        criteriaList.add(criterion);

        return ContractDefinitionRequest.builder()
                .id(id)
                .accessPolicyId(accessPolicyId)
                .contractPolicyId(usagePolicyId)
                .criteria(criteriaList)
                .build();

    }
}
