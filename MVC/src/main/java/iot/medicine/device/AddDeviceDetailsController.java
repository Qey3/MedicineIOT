package iot.medicine.device;

import my.entity.mvc.device.DevicesDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.logging.Logger;

@Controller
@RequestMapping("/addDeviceDetails")
public class AddDeviceDetailsController {

    private static Logger log = Logger.getLogger("AddDeviceTypeController");

    @Autowired
    DevicesService devicesService;

    @Autowired
    DeviceValidator validator;

    @GetMapping
    public String showAddPage(Model model) {

        model.addAttribute("types", devicesService.getAllTypesName());

        return "addDeviceDetails";
    }

    @PostMapping("/add")
    public String addNewProduct(@ModelAttribute("item") DevicesDetails devicesDetails,
                                @RequestParam("image") MultipartFile file,
                                BindingResult result,
                                Model model) throws IOException {

        validator.validate(devicesDetails, result);

        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            return "addDeviceDetails";
        }
        if (!file.isEmpty() && devicesDetails != null) {
            byte[] bytes = file.getBytes();

            log.info("IMAGE BYTES: " + bytes.length);

            devicesDetails.setPicture(bytes);
            if (devicesDetails.getDescription().isEmpty()) devicesDetails.setDescription("no data");

            devicesService.saveNewDevicesDetails(devicesDetails);
            return "redirect:/homePage";
        }

        return "error";
    }
}
