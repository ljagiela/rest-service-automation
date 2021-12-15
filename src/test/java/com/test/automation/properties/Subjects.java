package com.test.automation.properties;

import java.util.List;

import static com.test.automation.BaseTest.baseConfig;

public interface Subjects {
    List<String> validSubjects = baseConfig.getValidSubjects();
}
