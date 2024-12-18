# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),

## [Unreleased]

- Upgraded the base image of eclipse-temurin
- Added trufflehog
- Updated dash tool plugin

## [1.0.13] - 2024-07-18
### Fixed
- Dependabot spring-cloud, webmvc-ui and spring-boot version issues fixed.

## [1.0.12] - 2024-05-14
### Changed
- ARC 42 document update for the EDC 7 support.

### Fixed
- CVE-2024-22262 springframework URL Parsing with Host Validation security issue
- Multiple dependencies updated to maintain latest versions

### Added
- Support for edc v7

## [1.0.11] - 2024-05-02
### Fixed
- Base image upgraded due to vulnerabilities

## [1.0.10] - 2024-02-12
### Fixed
- Fixed veracode security CVE-2023-6378(logback-classic Denial Of Service)
- Upgrade Spring Boot to get rid of CVE-2023-46589 and CVE-2023-34053
- Fixed security issue for openssl
- Fixed preconfigured connector URL '/' issue
- Fixed dynamic asset name generation issue
- Fixed veracode security issue CVE-2023-6481

### Added
- Added unit tests for Controller and service

## [1.0.9] - 2023-08-31

### Changed
 - Updated documentation
 - Updated USERIDs when specifying the user for the Docker image
 - Updated Dockefile for image creation
 - Updated deployment with runAsUser
 - Updated the workflow for helm lint
 - Updated trivy workflow

### Removed
 - Removed values feom default values.yaml

## [1.0.8] - 2023-08-23

### Changed
 - Updated the helm lint
 
## [1.0.7] - 2023-08-16

### Changed
 - Updated the image tag in trivy
 
## [1.0.6] - 2023-08-09

### Changed
 - Updated the image to create in docker hub
 
## [1.0.5] - 2023-07-11

### Changed
 - Data exchange test service to support EDC 0.5.x

## [1.0.4] - 2023-07-06

### Changed
 - ARC42 document update
 
## [1.0.3] - 2023-06-28

### Changed
 - Data exchange test service to support EDC 0.4.1

## [1.0.2] - 2023-06-26

### Added
 - Addition of helm lint
 - 
## [1.0.1] - 2023-06-15

### Fixed
 - Upgraded spring boot library due to vulenerability
 - Upgraded spring cloud library due to vulenerability

## [1.0.0] - 2023-06-07

### Added
 - Addition of configuration for stable env

### Changed
- Updated Code of Conduct file

## [0.1.9] - 2023-05-02

### Added
 - Added .tractusx file in the repo

### Changed
- Updated librabries and upgrade spring boot


## [0.1.8] - 2023-04-24

### Fixed
- Fixed spring security web veracode security by upgrading its version to 6.0.3

## [0.1.7] - 2023-04-19

### Changed

 - Spring boot version updated to 3.0.5
 - Updated spring expression to 6.0.8

## [0.1.6] - 2023-04-11

### Added

 - Spring boot version update
 - UI refactoring
 - Test connector as a provider flow added
 - Response variable name change
 - UI page for testing connector
 - Addition of helm charts
 - Added AUTHORS.md, CHANGELOG.md, CODE_OF_CONDUCT.md, CONTRIBUTING.md, INSTALL.md, NOTICE.md, SECURITY.md file
 - Added LICENSE file
 - Open api changes
 - Fetch preconfigured connector url from properties file
 - Demo UI update and dependency issues resolved
 - Test connector api changes for auto-setup email support

### Removed
 - Use of old deprecated methods after version update 
 - Removed hardcoded url
