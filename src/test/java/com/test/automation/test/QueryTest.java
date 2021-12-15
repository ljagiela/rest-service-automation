package com.test.automation.test;

import com.test.automation.models.request.QueryRequest;
import com.test.automation.models.response.MetadataResponse;
import com.test.automation.pageObject.Metadata;
import com.test.automation.pageObject.Property;
import com.test.automation.pageObject.Query;
import com.test.automation.properties.Subjects;
import com.test.automation.utils.Hooks;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@Epic("QA task")
@Feature("Query post endpoint")
@ExtendWith({Hooks.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class QueryTest {
    Query query = new Query();
    Property property = new Property();
    Metadata metadata = new Metadata();

    @Test
    @DisplayName("Should return 2 valid subjects when 2 valid subjects are used")
    void shouldReturn2ValidSubjectsWhen2ValidSubjectsAreUsed() {
        List<String> subjects = new ArrayList<>(Subjects.validSubjects);
        QueryRequest queryRequest = QueryRequest.builder().subjects(subjects).build();
        List<MetadataResponse> metadataResponses = query.postQuery(queryRequest);
        assertThat(metadataResponses.size()).isEqualTo(2);
        metadata.verifyAllSubjectMetadataPropertiesAreNotNull(metadataResponses.get(0));
        metadata.verifyAllSubjectMetadataPropertiesAreNotNull(metadataResponses.get(1));
    }

    @Test
    @DisplayName("Should return 1 valid subject when 1 valid subject is used")
    void shouldReturn1ValidSubjectWhen1ValidSubjectIsUsed() {
        List<String> subjects = Collections.singletonList(Subjects.validSubjects.get(0));
        QueryRequest queryRequest = QueryRequest.builder().subjects(subjects).build();
        List<MetadataResponse> metadataResponses = query.postQuery(queryRequest);
        assertThat(metadataResponses.size()).isEqualTo(1);
        metadata.verifyAllSubjectMetadataPropertiesAreNotNull(metadataResponses.get(0));
    }

    @Test
    @DisplayName("Should return 1 valid subject when 1 valid subject is used and 1 invalid")
    void shouldReturn1ValidSubjectWhen1ValidSubjectIsUsedAnd1Invalid() {
        List<String> subjects = Arrays.asList(Subjects.validSubjects.get(0), "someSubject");
        QueryRequest queryRequest = QueryRequest.builder().subjects(subjects).build();
        List<MetadataResponse> metadataResponses = query.postQuery(queryRequest);
        assertThat(metadataResponses.size()).isEqualTo(1);
        metadata.verifyAllSubjectMetadataPropertiesAreNotNull(metadataResponses.get(0));
    }

    @Test
    @DisplayName("Should return 1 subject with expected properties when properties are passed")
    void shouldReturn1SubjectWithExpectedPropertiesWhenPropertiesArePassed() {
        List<String> subjects = Collections.singletonList(Subjects.validSubjects.get(0));
        List<String> properties = Arrays.asList("name", "description");

        QueryRequest queryRequest = QueryRequest.builder().subjects(subjects).properties(properties).build();
        List<MetadataResponse> metadataResponses = query.postQuery(queryRequest);

        assertThat(metadataResponses.size()).isEqualTo(1);
        metadata.verifyExpectedMetadataPropertiesAreNotNull(metadataResponses.get(0));
        property.verifyPropertyContainsAllNotNullData(metadataResponses.get(0).getName());
        property.verifyPropertyContainsAllNotNullData(metadataResponses.get(0).getDescription());

        assertThat(metadataResponses.get(0).getUrl()).isNull();
        assertThat(metadataResponses.get(0).getTicker()).isNull();
        assertThat(metadataResponses.get(0).getLogo()).isNull();

    }

    @Test
    @DisplayName("Should return no results for invalid subject")
    void shouldReturnNoResultsForInvalidSubject() {
        List<String> subjects = Collections.singletonList("someSubject");
        List<String> properties = Arrays.asList("name", "description");

        QueryRequest queryRequest = QueryRequest.builder().subjects(subjects).properties(properties).build();
        List<MetadataResponse> metadataResponses = query.postQuery(queryRequest);
        assertThat(metadataResponses.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("Should return only wanted results for unknown properties")
    void shouldReturnOnlyWantedResultsForUnknownProperties() {
        List<String> subjects = Collections.singletonList(Subjects.validSubjects.get(0));
        List<String> properties = Arrays.asList("someProp1", "someProp2");

        QueryRequest queryRequest = QueryRequest.builder().subjects(subjects).properties(properties).build();
        List<MetadataResponse> metadataResponses = query.postQuery(queryRequest);
        assertThat(metadataResponses.size()).isEqualTo(1);
        metadata.verifyExpectedMetadataPropertiesAreNotNull(metadataResponses.get(0));
        metadata.verifyUnwantedMetadataPropertiesAreNulls(metadataResponses.get(0));
    }
}