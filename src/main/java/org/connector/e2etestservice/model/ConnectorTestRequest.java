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
