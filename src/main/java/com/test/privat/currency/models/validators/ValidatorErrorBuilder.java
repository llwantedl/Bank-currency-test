package com.test.privat.currency.models.validators;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;

@Service
public interface ValidatorErrorBuilder {
    List<String> getErrors(BindingResult bindingResult);
    String getErrorsText(BindingResult bindingResult);
}
