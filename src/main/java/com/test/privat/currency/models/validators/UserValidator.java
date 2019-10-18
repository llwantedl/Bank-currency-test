package com.test.privat.currency.models.validators;

import com.test.privat.currency.models.dtolayer.wrappers.UserForm;
import com.test.privat.currency.models.services.CurrencyService;
import com.test.privat.currency.models.services.UserService;
import com.test.privat.currency.models.utils.FormValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    private static final int LOGIN_MINIMUM_CHARS = 4;
    private static final int LOGIN_MAXIMUM_CHARS = 16;
    private static final int PASSWORD_MINIMUM_CHARS = 4;
    private static final int PASSWORD_MAXIMUM_CHARS = 20;

    private final UserService userService;

    private final CurrencyService currencyService;

    @Autowired
    public UserValidator(UserService userService,
                         CurrencyService currencyService) {
        this.userService = userService;
        this.currencyService = currencyService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UserForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserForm user = (UserForm) o;

        String login = user.getLogin();
        String password = user.getPassword();
        String confirmPassword = user.getConfirmPassword();

        //USER LOGIN VALIDATE
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "required");
        if (login.length() < LOGIN_MINIMUM_CHARS || login.length() >= LOGIN_MAXIMUM_CHARS) {
            errors.rejectValue("login", "user-form.login.size");
        }

        //IS USER LOGIN NOT EXISTS VALIDATE
        if(userService.isLoginExists(login)){
            errors.rejectValue("login", "user-form.login.exist");
        }

        //USER EMAIL VALIDATE
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "required");
        if(!FormValidatorUtils.validate(user.getEmail())) {
            errors.rejectValue("email", "user-form.email.pattern");
        }

        //USER PASSWORD VALIDATE
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required");
        if(password.length() < PASSWORD_MINIMUM_CHARS || password.length() >= PASSWORD_MAXIMUM_CHARS){
            errors.rejectValue("password", "user-form.password.size");
        }

        //CONFIRM PASSWORD VALIDATE
        if(!password.equals(confirmPassword)){
            errors.rejectValue("confirmPassword", "user-form.password.different");
        }

        //CONFIRM THAT CURRENCY EXISTS IN THE DATABASE
        if(!currencyService.isExistsByKey(user.getCurrencyKey())){
            errors.rejectValue("currencyKey", "user-form.currency.database");
        }
    }
}
