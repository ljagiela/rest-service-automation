package com.test.automation.test;

import com.test.automation.models.request.PropertyRequest;
import com.test.automation.models.response.PropertyResponse;
import com.test.automation.pageObject.Property;
import com.test.automation.properties.Subjects;
import com.test.automation.utils.Hooks;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Epic("QA task")
@Feature("Property get endpoint")
@ExtendWith({Hooks.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PropertyTest {

    Property property = new Property();

    @ParameterizedTest(name = "should return non-null data for property : {0}")
    @ValueSource(strings = {"name", "description", "ticker", "logo", "url", "decimals"})
    void fullPropertyTest(String propertyName) {
        PropertyRequest propertyRequest = PropertyRequest.builder().property(propertyName).subject(Subjects.validSubjects.get(0)).build();
        PropertyResponse propertyResponse = property.getProperty(propertyRequest);
        property.verifyPropertyContainsAllNotNullData(propertyResponse);
    }

    @Test
    @DisplayName("Should return valid subject property")
    void shouldReturnValidSubjectProperty() {
        PropertyRequest propertyRequest = PropertyRequest.builder().property("subject").subject(Subjects.validSubjects.get(0)).build();
        Response response = property.sendGetRequest(propertyRequest);
        assertThat(response.getBody().asString()).isNotEmpty();
    }

    @Test
    @DisplayName("Should return valid policy property")
    void shouldReturnValidPolicyProperty() {
        PropertyRequest propertyRequest = PropertyRequest.builder().property("policy").subject(Subjects.validSubjects.get(0)).build();
        Response response = property.sendGetRequest(propertyRequest);
        assertThat(response.getBody().asString()).isNotEmpty();
    }

    @Test
    @DisplayName("Should return proper error message for invalid subject")
    void shouldReturnProperErrorMessageForInvalidSubject() {
        String testSubject = "someSubject";
        String expectedErrorMessage = "Requested subject '" + testSubject + "' not found";

        PropertyRequest propertyRequest = PropertyRequest.builder().property("policy").subject(testSubject).build();
        Response response = property.sendGetRequest(propertyRequest);
        assertThat(response.getBody().asString()).isEqualTo(expectedErrorMessage);
    }

    @Test
    @DisplayName("Should return proper error message for invalid property")
    void shouldReturnProperErrorMessageForInvalidProperty() {
        String testProperty = "someProperty";
        String expectedErrorMessage = "Requested property '" + testProperty + "' not found";

        PropertyRequest propertyRequest = PropertyRequest.builder().property(testProperty).subject(Subjects.validSubjects.get(0)).build();
        Response response = property.sendGetRequest(propertyRequest);
        assertThat(response.getBody().asString()).isEqualTo(expectedErrorMessage);
    }
}
