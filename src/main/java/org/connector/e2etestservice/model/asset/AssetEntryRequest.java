package org.connector.e2etestservice.model.asset;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AssetEntryRequest {
    private Asset asset;
    private DataAddressRequest dataAddress;

}
