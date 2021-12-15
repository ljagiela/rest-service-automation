package com.test.automation;


import org.aeonbits.owner.Config;

import java.util.List;

@Config.Sources({"file:src/test/resources/properties/${env}.properties"})
public interface BaseConfig extends Config {

    @Key("baseUrl")
    String getBaseUrl();

    @Key("validSubjects")
    List<String> getValidSubjects();

}
