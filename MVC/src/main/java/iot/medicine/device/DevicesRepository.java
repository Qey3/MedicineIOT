package iot.medicine.device;

import my.entity.mvc.SugarTestsMVC;
import my.entity.mvc.device.Device;
import my.entity.mvc.device.DeviceTypeName;
import my.entity.mvc.device.DeviceTypes;
import my.entity.mvc.device.DevicesDetails;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Repository
public class DevicesRepository {

    private static final Logger log = Logger.getLogger("DevicesRepository");

    @Autowired
    SessionFactory sessionFactory;

    public List<DevicesDetails> getAllDevicesDetails() {
        return sessionFactory.getCurrentSession()
                .createQuery("from DevicesDetails", DevicesDetails.class)
                .list();
    }

    public DevicesDetails getDevicesDetailsById(Long id) {
        return sessionFactory.getCurrentSession()
                .get(DevicesDetails.class, id);
    }

    public void saveNewDevicesDetails(DevicesDetails devicesDetails) {
        sessionFactory.getCurrentSession()
                .saveOrUpdate(devicesDetails);
    }

    public void delDevicesDetails(Long id) {
        sessionFactory.getCurrentSession()
                .delete(sessionFactory.getCurrentSession()
                        .load(DevicesDetails.class, id));
    }

    public List<Device> getUserDevices(String userName) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Device d where d.user = ( from Users u where u.login like :param1)", Device.class)
                .setParameter("param1", userName)
                .list();
    }

    public List<String> getAllTypesName() {
        return sessionFactory.getCurrentSession()
                .createQuery("select deviceTypeName from DeviceTypes", DeviceTypeName.class)
                .list()
                .stream()
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    public void saveNewUserDevice(Device device) {
        sessionFactory.getCurrentSession()
                .persist(device);
    }

    @Transactional("transactionManager")
    public Device getDeviceBySerialNumber(Long serialNumber) {
//        log.info("serialnumber = "+serialNumber);
        try {
            return sessionFactory.getCurrentSession()
                    .createQuery("from Device where serialNumber =:param1 and endUse is null", Device.class)
                    .setParameter("param1", serialNumber)
                    .getSingleResult();
        } catch (Exception e) {
            log.warning("getDeviceBySerialNumber warning "+e.getMessage());
            return null;
        }
    }

    public List<Device> getAllDevices() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Device", Device.class)
                .list();
    }

    public Device getDeviceById(Long id) {
        return sessionFactory.getCurrentSession()
                .get(Device.class, id);
    }

    public void disableDevice(Device device) {
        sessionFactory.getCurrentSession()
                .update(device);
    }

    public DeviceTypes getTypesByName(DeviceTypeName typeName) {
        try {
            return sessionFactory.getCurrentSession()
                    .createQuery("from DeviceTypes d where d.deviceTypeName like :typeName", DeviceTypes.class)
                    .setParameter("typeName", typeName)
                    .getSingleResult();
        } catch (Exception e) {
            log.info(e.getMessage());
            return null;
        }
    }
}
