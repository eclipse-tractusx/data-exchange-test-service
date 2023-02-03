package org.connector.e2etestservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.connector.e2etestservice.model.ConnectorTestRequest;
import org.connector.e2etestservice.service.TestConnectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class E2eTestController {

    private TestConnectorService testConnectorService;

    @Autowired
    public E2eTestController(TestConnectorService testConnectorService) {
        this.testConnectorService = testConnectorService;
    }

    @Operation(summary = "Request to test connector",
            description = "Request to test connector.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = String.class)))
            }
    )
    @PostMapping("/connector-test")
    public Object testConnector(@Valid @RequestBody ConnectorTestRequest connectorTestRequest) {
        boolean consumerTestResult = testConnectorService.testConnectorAsConsumer(connectorTestRequest);
        if(consumerTestResult) {
            return new ResponseEntity("Connector is working as a consumer", HttpStatus.OK);
        } else {
            return new ResponseEntity("Connector is not working as a consumer", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
