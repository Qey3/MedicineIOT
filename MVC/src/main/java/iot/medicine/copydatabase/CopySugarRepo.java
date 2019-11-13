package iot.medicine.copydatabase;

import my.entity.mvc.LastId;
import my.entity.mvc.SugarTestsMVC;
import my.entity.mvc.device.Device;
import my.entity.sugarMS.SugarTests;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class CopySugarRepo {

    private static final Logger log = Logger.getLogger("NotificationRepo");

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    @Qualifier("sessionFactory_sugar")
    SessionFactory sessionFactory_ms;

    public Long getLastId() {
        LastId last_id = sessionFactory.getCurrentSession()
                .get(LastId.class, 1L);
        if (last_id == null) return 0L;
        return last_id.getLast_id();
    }

    public void saveLastId(LastId lastId) {
        sessionFactory.getCurrentSession()
                .persist(lastId);
    }

    public List<SugarTests> getNewSugarTests(Long last_id) {
        return sessionFactory_ms.getCurrentSession()
                .createQuery("from sugartest where id > :param1", SugarTests.class)
                .setParameter("param1", last_id)
                .list();
    }

    public void copySugarTests(List<SugarTestsMVC> sugarTests) {
//        log.info("copySugarTests" + sugarTests.size() + "List.get(0) - " + sugarTests.get(0));
        for(SugarTestsMVC testsMVC : sugarTests){
            try {
                sessionFactory.getCurrentSession()
                        .save(testsMVC);
            } catch (Exception e) {
                log.warning("exception when save: " + e.getMessage());
            }
        }
    }


}
