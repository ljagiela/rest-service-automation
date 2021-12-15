package com.test.automation.pageObject;

import com.test.automation.models.request.PropertyRequest;
import com.test.automation.models.response.PropertyResponse;
import com.test.automation.properties.Endpoints;
import com.test.automation.properties.PathParams;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Property {

    List<String> unwantedProperties = Arrays.asList("name", "description", "ticker", "logo", "url", "unit");
    List<String> alwaysWantedProps = Arrays.asList("subject", "decimals", "policy");

    @Step("Send Get property request")
    public Response sendGetRequest(PropertyRequest propertyRequest) {
        return given()
                .when()
                .pathParam(PathParams.PROPERTY, propertyRequest.getProperty())
                .pathParam(PathParams.SUBJECT, propertyRequest.getSubject())
                .log().all()
                .get(Endpoints.PROPERTY_URL)
                .then()
                .log().all()
                .extract()
                .response();
    }

    @Step("Send Get property request")
    public PropertyResponse getProperty(PropertyRequest propertyRequest) {
        Response response = this.sendGetRequest(propertyRequest);
        assertThat(response.getStatusCode()).isEqualTo(HTTP_OK);
        return response.as(PropertyResponse.class);
    }

    @Step("Verify property: {0}")
    public void verifyPropertyContainsAllNotNullData(PropertyResponse property) {
        assertThat(property.getSequenceNumber()).isNotNull();
        assertThat(property.getValue()).isNotNull();
        assertThat(property.getSequenceNumber()).isNotNull();
        assertThat(property.getSignatures().size()).isGreaterThan(0);
        property.getSignatures().forEach(signature -> {
            assertThat(signature.getSignature()).isNotNull();
            assertThat(signature.getPublicKey()).isNotNull();
        });
    }
}
