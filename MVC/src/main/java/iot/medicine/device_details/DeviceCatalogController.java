package iot.medicine.device_details;

import iot.medicine.device.DevicesService;
import my.entity.mvc.device.DevicesDetails;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Controller
public class DeviceCatalogController {

    private static Logger log = Logger.getLogger("DeviceCatalogController");

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
