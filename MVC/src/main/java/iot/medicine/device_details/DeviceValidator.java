package iot.medicine.device_details;

import my.entity.mvc.device.Device;
import my.entity.mvc.device.DevicesDetails;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class DeviceValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Device.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if (o == null) {
            errors.reject("object.null", "Product item is null");
            return;
        }
        DevicesDetails devicesDetails = (DevicesDetails) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.empty", "The item name is empty");
    }
}
