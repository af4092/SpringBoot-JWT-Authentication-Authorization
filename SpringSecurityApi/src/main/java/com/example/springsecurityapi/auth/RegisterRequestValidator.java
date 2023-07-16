package com.example.springsecurityapi.auth;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class RegisterRequestValidator implements Validator {

    private static final String NAME_REGEX = "[a-zA-Z가-힣]{3,}";

    @Override
    public boolean supports(Class<?> clazz) {
        return RegisterRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RegisterRequest request = (RegisterRequest) target;

        if (StringUtils.isEmpty(request.getFirstname())) {
            errors.rejectValue("firstname", "field.required", "Firstname is required.");
        } else if (!request.getFirstname().matches(NAME_REGEX)) {
            errors.rejectValue("firstname", "field.invalid", "Firstname must contain only letters and be at least 3 characters long.");
        }

        if (StringUtils.isEmpty(request.getLastname())) {
            errors.rejectValue("lastname", "field.required", "Lastname is required.");
        } else if (!request.getLastname().matches(NAME_REGEX)) {
            errors.rejectValue("lastname", "field.invalid", "Lastname must contain only letters and be at least 3 characters long.");
        }
    }
}

