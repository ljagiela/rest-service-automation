package com.test.automation.models.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QueryRequest {
    List<String> subjects;
    @Builder.Default
    List<String> properties = new ArrayList<>();

    public String createJsonBody() {
        JSONObject body = new JSONObject();
        JSONArray subjectsArr = new JSONArray();
        JSONArray propertiesArr = new JSONArray();

        this.subjects.forEach(subjectsArr::put);
        body.put("subjects", subjectsArr);

        if (this.properties.size() > 0) {
            this.properties.forEach(propertiesArr::put);
            if (this.properties.size() > 0)
                body.put("properties", propertiesArr);
        }
        return body.toString();
    }
}
