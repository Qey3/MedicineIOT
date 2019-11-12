package iot.medicine.device;

import my.entity.mvc.device.DevicesDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DeviceItemController {

    @Autowired
    DevicesService devicesService;

    @GetMapping("device-catalog/item/{id}")
    public String showProductItem(@PathVariable Long id, Model model){
        DevicesDetails deviceDetails = devicesService.getDeviceDetails(id);
        model.addAttribute("device", deviceDetails);
        return "deviceItemPage";
    }

    @RequestMapping(value = "device-catalog/item/{id}/image")
    @ResponseBody
    public byte[] showProductImage(@PathVariable Long id, Model model){
        return devicesService.getDeviceDetails(id).getPicture();
    }

    @RequestMapping(value = "device-catalog/item/{id}/del")
    public String delProductItem(@PathVariable Long id){
        devicesService.delDevicesDetails(id);
        return "redirect:/homePage";
    }
}
