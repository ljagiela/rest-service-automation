package com.test.automation.pageObject;

import com.test.automation.models.response.MetadataResponse;
import com.test.automation.properties.Endpoints;
import com.test.automation.properties.PathParams;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Metadata {

    Property property = new Property();

    @Step("Send Get metadata request")
    public Response sendGetRequest(String subject) {
        return given()
                .when()
                .pathParam(PathParams.SUBJECT, subject)
                .get(Endpoints.METADATA_URL)
                .then()
                .extract()
                .response();
    }

    @Step("Send Get metadata request")
    public MetadataResponse getMetadata(String subject) {
        Response response = this.sendGetRequest(subject);
        assertThat(response.getStatusCode()).isEqualTo(HTTP_OK);
        return response.as(MetadataResponse.class);
    }

    @Step("Verify all subject metadata properties are not null: {0}")
    public void verifyAllSubjectMetadataPropertiesAreNotNull(MetadataResponse metadataResponse) {
        this.verifyExpectedMetadataPropertiesAreNotNull(metadataResponse);
        property.verifyPropertyContainsAllNotNullData(metadataResponse.getUrl());
        property.verifyPropertyContainsAllNotNullData(metadataResponse.getName());
        property.verifyPropertyContainsAllNotNullData(metadataResponse.getTicker());
        property.verifyPropertyContainsAllNotNullData(metadataResponse.getLogo());
        property.verifyPropertyContainsAllNotNullData(metadataResponse.getDescription());
    }

    @Step("Verify expected metadata properties are not nulls: {0}")
    public void verifyExpectedMetadataPropertiesAreNotNull(MetadataResponse metadataResponse) {
        assertThat(metadataResponse.getSubject()).isNotNull();
        assertThat(metadataResponse.getPolicy()).isNotNull();
        property.verifyPropertyContainsAllNotNullData(metadataResponse.getDecimals());
    }

    @Step("Verify unwanted metadata properties are nulls: {0}")
    public void verifyUnwantedMetadataPropertiesAreNulls(MetadataResponse metadataResponse) {
        assertThat(metadataResponse.getUrl()).isNull();
        assertThat(metadataResponse.getName()).isNull();
        assertThat(metadataResponse.getTicker()).isNull();
        assertThat(metadataResponse.getLogo()).isNull();
        assertThat(metadataResponse.getDescription()).isNull();
    }
}
