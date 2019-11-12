package iot.medicine.user;

import my.entity.mvc.user.Users;
import my.entity.mvc.device.Device;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Device.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if (o == null) {
            errors.reject("object.null", "User data is null");
            return;
        }
        Users users = (Users) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.empty", "User name is empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "login.empty", "Login is empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birthday", "birthday.empty", "The birthday is empty");
    }
}
