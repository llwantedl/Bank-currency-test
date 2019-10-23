package com.test.privat.currency.models.validators;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ValidatorErrorBuilderImpl implements ValidatorErrorBuilder {

    private static final String ERRORS_RESOURCES_FAIL = "Failed to get errors resources!";
    private final Logger logger = LoggerFactory.getLogger(ValidatorErrorBuilderImpl.class);

    @Override
    public List<String> getErrors(BindingResult bindingResult) {
        ResourceBundle bundle;

        try {
            bundle = getValidationBundle();
        } catch (MissingResourceException e) {
            return getBasicErrorList(e);
        }

        List<ObjectError> allErrors = bindingResult.getAllErrors();

        return allErrors
                .stream()
                .map(e -> getErrorText(bundle, e))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public String getErrorsText(BindingResult bindingResult) {
        ResourceBundle bundle;

        try {
            bundle = getValidationBundle();
        } catch (MissingResourceException e) {
            return "Failed to get errors resources!";
        }

        StringBuilder error = new StringBuilder();

        bindingResult
                .getAllErrors()
                .forEach(e -> error.append(getErrorText(bundle, e)));

        return error.toString();
    }

    private List<String> getBasicErrorList(MissingResourceException e) {
        logger.error(ERRORS_RESOURCES_FAIL + " " + e.getMessage());
        ArrayList<String> defaultError = new ArrayList<>();
        defaultError.add("Failed to get errors resources!");
        return defaultError;
    }

    private ResourceBundle getValidationBundle() throws MissingResourceException{
        return ResourceBundle.getBundle("validation-errors");
    }

    private String getErrorText(ResourceBundle bundle, ObjectError e) {
        String code = e.getCode();

        if (Objects.isNull(code)) {
            return null;
        }

        return bundle.getString(code);
    }
}
