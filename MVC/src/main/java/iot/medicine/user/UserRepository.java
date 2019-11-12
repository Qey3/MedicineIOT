package iot.medicine.user;

import my.entity.mvc.user.Users;
import my.entity.mvc.device.Device;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
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
        }catch (Exception e){
            log.warning(e.getMessage());
            return null;
        }
    }

    public List<Device> findUserDeviceByLogin(String userName) {
        try {
            return sessionFactory.getCurrentSession()
//                    .createQuery("from Users where Users.login like :param1", Users.class)
                    .createQuery("from Device d where d.user = (from Users as u where login like :param1)", Device.class)
                    .setParameter("param1", userName)
                    .list();
        } catch (Exception e) {
            log.warning(e.getMessage());
            return new ArrayList<>();
        }
    }
}
