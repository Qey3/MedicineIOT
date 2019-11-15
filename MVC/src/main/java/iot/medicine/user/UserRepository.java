package iot.medicine.user;

import my.entity.mvc.device.DevicesDetails;
import my.entity.mvc.user.RoleName;
import my.entity.mvc.user.Roles;
import my.entity.mvc.user.Users;
import my.entity.mvc.device.Device;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

@Repository
public class UserRepository {

    private static Logger log = Logger.getLogger("AppUserRepository");

    @Autowired
    SessionFactory sessionFactory;

    public Integer findUserCountByLogin(String username) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Users where login like :param1", Users.class)
                .setParameter("param1", username)
                .list()
                .size();
    }

    public void save(Users user) {
        sessionFactory.getCurrentSession()
                .persist(user);
    }


    public Users findUserByLogin(String username) {
        try {
            return sessionFactory.getCurrentSession()
                    .createQuery("from Users where login like :param1", Users.class)
                    .setParameter("param1", username)
                    .getSingleResult();
        } catch (Exception e) {
            log.warning(e.getMessage());
            return null;
        }
    }

    public List<Device> findUserDeviceByLogin(String userName) {
        try {
            List<Device> devices = sessionFactory.getCurrentSession()
                    .createQuery("from Device d where d.user = (from Users as u where login like :param1)", Device.class)
                    .setParameter("param1", userName)
                    .list();
            devices.forEach(Device::getDevicesDetails);
            return devices;
        } catch (Exception e) {
            log.warning(e.getMessage());
            return new ArrayList<>();
        }
    }

    public List<Users> getAllUsers() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Users", Users.class)
                .list();
    }
}
