package org.connector.e2etestservice.model.asset;

import org.connector.e2etestservice.constants.EDCAssetConstant;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

@Service
public class AssetFactory {


    public AssetEntryRequest generateDummyAssetObejct() {

        HashMap<String, String> assetProperties = new HashMap<>();
        assetProperties.put(EDCAssetConstant.ASSET_PROP_ID, "200");
        assetProperties.put(EDCAssetConstant.ASSET_PROP_DESCRIPTION, "Dummy data offer");

        HashMap<String, String> dataAddressProperties = new HashMap<>();
        dataAddressProperties.put("type", "HttpData");
        dataAddressProperties.put("endpoint", "https://jsonplaceholder.typicode.com/todos/3");

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
