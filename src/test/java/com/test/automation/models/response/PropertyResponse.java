package com.test.automation.models.response;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class PropertyResponse {
    int sequenceNumber;
    String value;
    List<SignatureResponse> signatures;
}
