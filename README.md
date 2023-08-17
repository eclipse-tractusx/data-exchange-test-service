# E2E Data Exchange Test Service


## Description

This repository is part of the overarching eclipse-tractusx project. It contains the Backend for the End to end data exchange test service.

It is a standalone service which can be self-hosted.
This service will help for testing the connectors and also can be used for health checks, and monitoring purpose.

### Software Version
```shell
Latest Helm version is v1.0.8
Latest Application version is v1.0.8
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
- 0.4.1

# Container images

This application provides container images for demonstration purposes. The base image used, to build this demo application image is eclipse-temurin:17-jdk-alpine

## Notice for Docker image

DockerHub: [https://hub.docker.com/r/tractusx/dataex](https://hub.docker.com/r/tractusx/dataex)  <br />
Eclipse Tractus-X product(s) installed within the image:

__Data Exchange Test Service__

- GitHub: https://github.com/eclipse-tractusx/data-exchange-test-service
- Project home: https://projects.eclipse.org/projects/automotive.tractusx
- Dockerfile: https://github.com/eclipse-tractusx/data-exchange-test-service/blob/main/Dockerfile
- Project license: [Apache License, Version 2.0](https://github.com/eclipse-tractusx/data-exchange-test-service/blob/main/LICENSE)

**Used base image**

- 17-jdk-alpine(https://hub.docker.com/layers/library/eclipse-temurin/17.0.6_10-jdk-alpine/images/sha256-c093675e143dc8023fb218c144e06491154491a7965d0664a93f99ada5259ec7?context=explore)
- Official Eclipse Temurin DockerHub page: https://hub.docker.com/_/eclipse-temurin
- Eclipse Temurin Project: https://projects.eclipse.org/projects/adoptium.temurin
- Additional information about the Eclipse Temurin images: https://github.com/docker-library/repo-info/tree/master/repos/eclipse-temurin

As with all Docker images, these likely also contain other software which may be under other licenses 
(such as Bash, etc. from the base distribution, along with any direct or indirect dependencies of the primary software being contained).

As for any pre-built image usage, it is the image user's responsibility to ensure that any use of this image complies with any relevant licenses for all software contained within.

## Installation Steps

[INSTALL.md](INSTALL.md)


### Licenses
Apache 2.0 (https://www.apache.org/licenses/LICENSE-2.0)
