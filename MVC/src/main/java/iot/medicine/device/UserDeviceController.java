package iot.medicine.device;

import my.entity.mvc.device.Device;
import iot.medicine.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Controller
public class UserDeviceController {

    private static final Logger log = Logger.getLogger("UserDeviceController");

    @Autowired
    DevicesService devicesService;

    @Autowired
    UserService userService;

    @Autowired
    UserDeviceValidator validator;

    @GetMapping("/user-device-catalog")
    public String showCatalog(Model model, Principal principal){

        String userLogin = principal.getName();

        List<Device> userDevice = new ArrayList<>();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        boolean hasUserRole = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_USER"));

        if (hasUserRole) {
            log.info("userLogin = " + userLogin);
            userDevice = userService.getUserDeviceByName(userLogin);
        } else {
            userDevice = userService.getAllDevices();
        }

        model.addAttribute("device", userDevice);

        return "userDeviceCatalog";
    }

    @GetMapping("/addUserDevice")
    public String showAddUserDevicePage(Model model) {

        model.addAttribute("types", devicesService.getAllTypesName());

        return "addUserDevice";
    }

    @PostMapping("/addUserDevice/add")
    public String addNewProduct(@ModelAttribute("item") Device device,
                                BindingResult result,
                                Principal principal,
                                Model model) throws IOException {

        validator.validate(device, result);

        String userLogin = principal.getName();

        if (result.hasErrors()) {
            model.addAttribute("types", devicesService.getAllTypesName());
            model.addAttribute("errors", result.getAllErrors());
            return "addUserDevice";
        }
        if (device != null) {

            devicesService.saveNewUserDevices(device, userLogin);
            return "redirect:/homePage";
        }

        return "error";
    }

    @GetMapping("/device-catalog/item/disable/{id}")
    public String disableUserDevice(@PathVariable Long id, Model model){

        devicesService.disableDevice(id);

        return "redirect:/user-device-catalog";
    }



}
