package com.test.automation.properties;

import static com.test.automation.BaseTest.baseConfig;

public interface Endpoints {
    String BASE_URL = baseConfig.getBaseUrl();
    String PROPERTY_URL = BASE_URL + "metadata/{" + PathParams.SUBJECT + "}/properties/{" + PathParams.PROPERTY + "}";
    String METADATA_URL = BASE_URL + "metadata/{" + PathParams.SUBJECT + "}";
    String QUERY_URL = BASE_URL + "metadata/query";
}
