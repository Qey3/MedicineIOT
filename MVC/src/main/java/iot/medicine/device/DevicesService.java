package iot.medicine.device;

import iot.medicine.user.UserRepository;
import my.entity.mvc.SugarTestsMVC;
import my.entity.mvc.device.Device;
import my.entity.mvc.device.DevicesDetails;
import my.entity.mvc.user.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class DevicesService {

    @Autowired
    DevicesRepository devicesRepository;

    @Autowired
    UserRepository userRepository;

    @Transactional("transactionManager")
    public List<DevicesDetails> getAllDevicesDetails() {
        return devicesRepository.getAllDevicesDetails();
    }

    @Transactional("transactionManager")
    public DevicesDetails getDeviceDetails(Long id) {
        return devicesRepository.getDevicesDetailsById(id);
    }

    @Transactional("transactionManager")
    public void saveNewDevicesDetails(DevicesDetails device) {
        devicesRepository.saveNewDevicesDetails(device);
    }

    @Transactional("transactionManager")
    public void delDevicesDetails(Long id) {
        devicesRepository.delDevicesDetails(id);
    }

    @Transactional("transactionManager")
    public List<Device> getUserDevices(String userName) {
        return devicesRepository.getUserDevices(userName);
    }

    @Transactional("transactionManager")
    public List<String> getAllTypesName() {
        return devicesRepository.getAllTypesName();
    }

    @Transactional("transactionManager")
    public void saveNewUserDevices(Device device, String userLogin) {

        DevicesDetails details = getAllDevicesDetails().get((int)(Math.random()*(getAllDevicesDetails().size()-1)));

        device.setDevicesDetails(details);
        device.setStartUse(new Date());
        device.setUser(userRepository.findUserByLogin(userLogin));

        devicesRepository.saveNewUserDevice(device);
    }

    @Transactional("transactionManager")
    public void disableDevice(Long id) {

        Device device = devicesRepository.getDeviceById(id);
        device.setEndUse(new Date());

        devicesRepository.disableDevice(device);
    }

    @Transactional("transactionManager")
    public Device getDeviceBySerialNumber(Long serialNumber) {
        return devicesRepository.getDeviceBySerialNumber(serialNumber);
    }

    public List<SugarTestsMVC> getAllSugarTestsByDevice(Device device) {
        return devicesRepository.getAllSugarTestsByDevice(device);

    }
}
