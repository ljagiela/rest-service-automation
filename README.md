# tested-service service test automation
#
## Running tests ##

Tested environment can be configured with `env` project property. 
Possible values are `stg`, `dev`, `prod`. 
Default value is `stg`.

Run all tests (stg):
```bash
./gradlew clean test
```
Supported params:
* `--tests E2E.e2e` - to run specific test (E2E suite -> e2e method/test)
* `-Penv=(localhost|stg|dev)` - to run on specific env

## Reporting ##
To generate and host reports locally run:
```bash
./gradlew allureReport allureServe
```

## Development tools
* [Junit5](https://junit.org/junit5/) - test platform
* [AssertJ](https://assertj.github.io/doc/) - assertions library
* [Allure](https://docs.qameta.io/allure/) - reporting framework