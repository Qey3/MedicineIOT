package iot.medicine.device;

import iot.medicine.user.UserService;
import my.entity.mvc.device.Device;
import my.entity.mvc.device.DevicesDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UserDeviceValidator implements Validator {

    @Autowired
    DevicesService devicesService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Device.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Pattern p = Pattern.compile("^[0-9]{8,15}$");

        if (o == null) {
            errors.reject("object.null", "Product item is null");
            return;
        }
        Device device = (Device) o;
        try {
            if (device.getSerialNumber().toString().isEmpty()){
                errors.reject("S/N is null");
                return;
            }
        } catch (Exception e) {
            errors.rejectValue("serialNumber", "serialNumber.null", "Incorrect S/N");
            return;
        }
        Matcher m = p.matcher(device.getSerialNumber().toString());

//        if (device.getSerialNumber() == null || device.getSerialNumber()<8 || device.getSerialNumber() > 12){
        if (!m.matches()){
            errors.rejectValue("serialNumber", "serialNumber.null", "Incorrect S/N");
        }
        Device ifExist = devicesService.getDeviceBySerialNumber(device.getSerialNumber());
        if (ifExist != null && ifExist.getSerialNumber().equals(device.getSerialNumber())){
            errors.rejectValue("serialNumber", "serialNumber.null", "already registered");
        }
    }
}
