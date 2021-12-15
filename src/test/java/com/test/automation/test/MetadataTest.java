package com.test.automation.test;

import com.test.automation.models.response.MetadataResponse;
import com.test.automation.pageObject.Metadata;
import com.test.automation.properties.Subjects;
import com.test.automation.utils.Hooks;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Epic("QA task")
@Feature("Metadata get endpoint")
@ExtendWith({Hooks.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MetadataTest {

    Metadata metadata = new Metadata();

    @Test
    @DisplayName("Should return valid metadata for valid subject")
    void shouldReturnValidMetadataForValidSubject() {
        MetadataResponse queryResponse = metadata.getMetadata(Subjects.validSubjects.get(0));
        metadata.verifyAllSubjectMetadataPropertiesAreNotNull(queryResponse);
    }

    @Test
    @DisplayName("Should return proper error message for invalid subject")
    void shouldReturnProperErrorMessageForInvalidSubject() {
        String testSubject = "someSubject";
        String expectedErrorMessage = "Requested subject '" + testSubject + "' not found";

        Response response = metadata.sendGetRequest("someSubject");
        assertThat(response.getBody().asString()).isEqualTo(expectedErrorMessage);
    }
}
