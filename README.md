# E2E Data Exchange Test Service


## Description

This repository is part of the overarching eclipse-tractusx project. It contains the Backend for the End to end data exchange test service.

It is a standalone service which can be self-hosted.
This service will help for testing the connectors and also can be used for health checks, and monitoring purpose.

### Software Version
```shell
Latest Helm version is v1.0.15
Latest Application version is v1.0.12
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
1. Clone the GitHub Repository - https://github.com/eclipse-tractusx/data-exchange-test-service
2. Setup your project environment to JDK 18
3. Start the application from your IDE.

## API authentication
Currently, authentication is not supported for this service

### EDC Version Supported
- 0.4.x
- 0.5.x (Cross version testing is not supported between 0.4 and 0.5)

# Container images

This application provides container images for demonstration purposes. The base image used, to build this demo application image is eclipse-temurin:17-jdk-alpine

## Notice for Docker image

 [DOCKER_NOTICE.md](DOCKER_NOTICE.md)

## Installation Steps

[INSTALL.md](INSTALL.md)

## Updating the `DEPENDENCIES` file

To update the [DEPENDENCIES](./DEPENDENCIES) declarations, run:

```shell
./mvnw org.eclipse.dash:license-tool-plugin:license-check 
```


### Licenses
Apache 2.0 (https://www.apache.org/licenses/LICENSE-2.0)
