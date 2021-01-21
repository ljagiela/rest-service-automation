package com.peer39.testedservice.automation;

import com.peer39.testedservice.automation.properties.BaseConfig;
import com.peer39.testedservice.automation.utils.AllureUtils;
import com.peer39.testedservice.automation.utils.RestAssuredUtils;
import org.aeonbits.owner.ConfigFactory;

public class BaseTest {
    public static String TEST_ENV = System.getProperty("env", "stg");
    public static BaseConfig baseConfig = ConfigFactory.create(BaseConfig.class);

    static {
        AllureUtils.setupAllure();
        RestAssuredUtils.setupRestAssured();
    }
}
