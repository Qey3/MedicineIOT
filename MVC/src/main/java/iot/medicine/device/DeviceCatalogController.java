package iot.medicine.device;

import my.entity.mvc.device.DevicesDetails;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DeviceCatalogController {

    @Autowired
    DevicesService devicesService;

    @GetMapping("/device-catalog")
    public String showCatalog(Model model){

        List<DevicesDetails> devicesDetails = new ArrayList<>();
        devicesDetails = devicesService.getAllDevicesDetails();
        model.addAttribute("device", devicesDetails);

        return "deviceCatalog";
    }

}
