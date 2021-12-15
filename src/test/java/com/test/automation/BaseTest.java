package com.test.automation;

import com.test.automation.utils.AllureUtils;
import com.test.automation.utils.RestAssuredUtils;
import org.aeonbits.owner.ConfigFactory;

public class BaseTest {
    public static String TEST_ENV = System.getProperty("env", "stg");
    public static BaseConfig baseConfig = ConfigFactory.create(BaseConfig.class);

    static {
        AllureUtils.setupAllure();
        RestAssuredUtils.setupRestAssured();
    }
}
