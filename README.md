# Tutorial: How to Prevent Reactive Java Applications from Stalling

This repository contains all the code for the Reactive Java tutorial, illustrating how to use schedulers for encapsulating blocking code.

**Prerequisites**:
- [Java 11+](https://openjdk.java.net/install/index.html)
- [Okta CLI](https://cli.okta.com)

## Getting Started

To install this example, run the following commands:
```bash
git clone https://github.com/indiepopart/reactive-service.git
```

## Configure the reactive-service

```shell
cd reactive-service
```

With OktaCLI, register for a free developer account:

```shell
okta register
```
Provide the required information. Once you complete the registration, create a client application with the following command:

```shell
okta apps create
```
You will be prompted to select the following options:

- Application name: reactive-service
- Type of Application: Web
- Type of Application: Okta Spring Boot Starter
- Redirect URI: Default
- Post Logout Redirect URI: Default

The OktaCLI will create the client application and configure the issuer, clientId and clientSecret in `src/main/resources/application.properties`.


## Run the application

```shell
./mvnw spring-boot:run
```

Open an incognito window and go to http://localhost:8080/random. First, the authentication flow will redirect to Okta for the login. Sign in with your Okta credentials.After the authorization, the search result will show a response similar to the following:

```json
{
value: "-1891300439"
}
```

## Run a Test Class

```shell
./mvnw test -Dtest=SchedulersTest
```
