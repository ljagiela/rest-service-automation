# mock-service test automation
#
## Running tests ##

Tested environment can be configured with `env` project property. 
Possible values are `localhost`, `stg`. 
Default value is `stg`.

Run all tests (stg):
```bash
./gradlew clean test
```
Supported params:
* `--tests MetadataTest.shouldReturnValidMetadataForValidSubject` - to run specific test (MetadataTest (class) suite -> shouldReturnValidMetadataForValidSubject method/test)
* `-Penv=(localhost|stg)` - to run on specific env

## Reporting ##
To generate and host reports locally run:
```bash
./gradlew allureReport allureServe
```

## Development tools
* [Junit5](https://junit.org/junit5/) - test platform
* [AssertJ](https://assertj.github.io/doc/) - assertions library
* [Allure](https://docs.qameta.io/allure/) - reporting framework