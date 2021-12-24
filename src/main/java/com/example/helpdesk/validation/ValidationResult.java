package com.example.helpdesk.validation;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ValidationResult {

    private boolean isValid;
    private String errorMessage;

}
