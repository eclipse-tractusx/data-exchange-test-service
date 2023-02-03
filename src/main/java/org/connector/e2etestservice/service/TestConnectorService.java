package org.connector.e2etestservice.service;

import lombok.extern.slf4j.Slf4j;
import org.connector.e2etestservice.api.QueryDataOffersProxy;
import org.connector.e2etestservice.facilitator.AbstractEDCStepsHelper;
import org.connector.e2etestservice.model.ConnectorTestRequest;
import org.connector.e2etestservice.model.contractoffers.ContractOffersCatalogResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
@Slf4j
public class TestConnectorService extends AbstractEDCStepsHelper {

    private String testConnectorUrl;
    private String testConnectorApiKeyHeader;
    private String testConnectorApiKey;

    private QueryDataOffersProxy queryDataOffersProxy;

    @Autowired
    public TestConnectorService(@Value("${default.edc.hostname}") String testConnectorUrl,
                                @Value("${default.edc.apiKeyHeader}") String testConnectorApiKeyHeader,
                                @Value("${default.edc.apiKey}") String testConnectorApiKey,
                                QueryDataOffersProxy queryDataOffersProxy) {
        this.testConnectorUrl = testConnectorUrl;
        this.testConnectorApiKeyHeader = testConnectorApiKeyHeader;
        this.testConnectorApiKey = testConnectorApiKey;
        this.queryDataOffersProxy = queryDataOffersProxy;
    }

    public boolean testConnectorAsConsumer(ConnectorTestRequest companyConnectorRequest) {
        try {
            URI companyConnectorURI = URI.create(companyConnectorRequest.getConnectorHost());
            ContractOffersCatalogResponse contractOfferCatalog = queryDataOffersProxy.getContractOffersCatalog(
                    companyConnectorURI,
                    getAuthHeader(companyConnectorRequest.getApiKeyHeader(), companyConnectorRequest.getApiKeyValue()),
                    testConnectorUrl + "/api/v1/ids/data",
                    10
            );

            log.info("Number of data offers fetched: " + contractOfferCatalog.getContractOffers().size());

            //        for (ContractOffer contractOffer : contractOfferCatalog.getContractOffers()) {
            //            log.info(contractOffer.getAsset().getProperties().get("asset:prop:name"));
            //            log.info(contractOffer.getAsset().getProperties().get("asset:prop:id"));
            //            log.info("-----------\n");
            //        }
        } catch (Exception e) {
            log.info("Exception occurred");
            return false;
        }
        return true;
    }

}
