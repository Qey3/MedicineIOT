package iot.medicine.sugar;

import my.entity.mvc.SugarTestsMVC;
import my.entity.mvc.device.Device;
import my.entity.mvc.user.Users;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Repository
public class SugarRepository {

    private static final Logger log = Logger.getLogger("SugarRepository");

    @Autowired
    SessionFactory sessionFactory;

    public List<SugarTestsMVC> findTests(int page, String userLogin) {
        int maxResult = 20;
        if (page > 1) page = (page - 1) * 20;
        else page = 0;
        try {
            return sessionFactory.getCurrentSession()
    //                .createQuery("from sugartestsmvc where login like :param1", SugarTestsMVC.class)
                    .createQuery("from sugartestsmvc s where s.device in (" +
                            "from Device d where d.user = (" +
                            "from Users s where s.login like :param1))", SugarTestsMVC.class)
                    .setParameter("param1", userLogin)
                    .setFirstResult(page)
                    .setMaxResults(maxResult)
                    .list();
        } catch (Exception e) {
            log.warning(e.getMessage());
            return new ArrayList<>();
        }

//        Users user = sessionFactory.getCurrentSession()
//                .createQuery("from Users s where s.login like :param1", Users.class)
//                .setParameter("param1", userLogin)
//                .getSingleResult();
//        List<Device> devices = user.getUsersDevice();
//
//        List<SugarTestsMVC> sugarTestsMVCS = new ArrayList<>();
//        devices.forEach(device -> sugarTestsMVCS.addAll(device.getSugarTestMVC()));
//        return sugarTestsMVCS;
    }
}
