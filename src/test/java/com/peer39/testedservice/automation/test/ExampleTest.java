package com.peer39.testedservice.automation.test;

import com.peer39.testedservice.automation.utils.Hooks;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.logging.Logger;

import static com.peer39.testedservice.automation.BaseTest.baseConfig;
import static org.assertj.core.api.Assertions.assertThat;


@Epic("Epic name")
@Feature("Feature name")
@ExtendWith({Hooks.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ExampleTest {
    private final Logger LOG = Logger.getLogger(getClass().getName());

    @Test
    @Issue("PEERTEAMB-1234")
    @DisplayName("test display name")
    void testDisplayName() {
        LOG.info("example test has been executed");
        LOG.info((baseConfig.somePropertyName()));
        assertThat(true).isTrue();
    }
}