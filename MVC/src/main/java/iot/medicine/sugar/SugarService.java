package iot.medicine.sugar;

import iot.medicine.device.DevicesService;
import my.entity.mvc.SugarTestsMVC;
import my.entity.mvc.device.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(value = "transactionManager")
public class SugarService {

    @Autowired
    SugarRepository reposytory;

    @Autowired
    DevicesService devicesService;

    public List<SugarTestsMVC> getTests(int page, String userName) {

        List<Device> userDevices = devicesService.getUserDevices(userName);

        List<SugarTestsMVC> sugar = new ArrayList<>();

        for(Device device : userDevices){
            sugar.addAll(devicesService.getAllSugarTestsByDevice(device));
        }
        return reposytory.findTests(page, userName);
    }
}
