package com.test.automation.models.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class MetadataResponse {
    String subject;
    PropertyResponse url;
    PropertyResponse name;
    PropertyResponse ticker;
    PropertyResponse decimals;
    String policy;
    PropertyResponse logo;
    PropertyResponse description;
}
