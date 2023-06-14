# E2E Data Exchange Test Service


## Description

This repository is part of the overarching eclipse-tractusx project. It contains the Backend for the End to end data exchange test service.

It is a standalone service which can be self-hosted.
This service will help for testing the connectors and also can be used for health checks, and monitoring purpose.

### Software Version
```shell
Latest Helm version is v1.0.1
Latest Application version is v1.0.1
```
### How to run

This service is a SpringBoot Java software project managed by Maven.

To run this service you need details of the test connector which is supposed to be preconfigured.
You need:
1. Test connector hostname
2. Test connector API Key header
3. Test connector API key
Store all above values in application properties file.

### Prerequisites
- JDK18

### Steps
1. Clone the GitHub Repository - https://github.com/catenax-ng/tx-data-exchange-test-service
2. Setup your project environment to JDK 18
3. Start the application from your IDE.

## API authentication
Currently, authentication is not supported for this service


## Installation Steps

[INSTALL.md](INSTALL.md)


### Licenses
Apache 2.0 (https://www.apache.org/licenses/LICENSE-2.0)
