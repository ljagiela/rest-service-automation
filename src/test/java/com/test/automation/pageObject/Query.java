package com.test.automation.pageObject;

import com.test.automation.models.request.QueryRequest;
import com.test.automation.models.response.MetadataResponse;
import com.test.automation.properties.Endpoints;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Query {

    @Step("Send Query post request")
    public Response sendPostRequest(QueryRequest queryRequest) {
        return given()
                .when()
                .body(queryRequest.createJsonBody())
                .post(Endpoints.QUERY_URL)
                .then()
                .extract()
                .response();
    }

    @SneakyThrows
    @Step("Send query post request")
    public List<MetadataResponse> postQuery(QueryRequest queryRequest) {
        Response response = this.sendPostRequest(queryRequest);
        assertThat(response.getStatusCode()).isEqualTo(HTTP_OK);
        List<MetadataResponse> results = response
                .jsonPath()
                .getList("subjects", MetadataResponse.class);
        return results.size() > 0 ? results : new ArrayList<>();
    }
}
