package com.peer39.testedservice.automation.properties;


import org.aeonbits.owner.Config;

@Config.Sources({"file:src/test/resources/properties/${env}.properties"})
public interface BaseConfig extends Config {

    @Key("some.property.name")
    String somePropertyName();

}
