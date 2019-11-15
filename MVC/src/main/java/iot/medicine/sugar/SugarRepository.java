package iot.medicine.sugar;

import my.entity.mvc.SugarTestsMVC;
import my.entity.mvc.device.Device;
import my.entity.mvc.user.Users;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Repository
public class SugarRepository {

    private static final Logger log = Logger.getLogger("SugarRepository");

    @Autowired
    SessionFactory sessionFactory;

    public List<SugarTestsMVC> findTests(int page, String userLogin, Double glucose) {
        int maxResult = 20;
        page = page > 1 ? (page -1) * 20: 0;
        try {
            return sessionFactory.getCurrentSession()
    //                .createQuery("from sugartestsmvc where login like :param1", SugarTestsMVC.class)
                    .createQuery("from sugartestsmvc s where s.device in (" +
                            "from Device d where d.user = (" +
                            "from Users s where s.login like :param1)) and s.glucose > :gluc" +
                            " order by analysisTime desc", SugarTestsMVC.class)
                    .setParameter("param1", userLogin)
                    .setParameter("gluc", glucose)
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

    public Collection<? extends SugarTestsMVC> getAllSugarTestsByDevice(Device device, Double glucose) {
            Date end = device.getEndUse() == null? new Date(): device.getEndUse();

            return sessionFactory.getCurrentSession()
                    .createQuery("from sugartestsmvc s where s.device = :device and s.analysisTime " +
                            "BETWEEN :stDate AND :edDate and s.glucose > :gluc", SugarTestsMVC.class)
                    .setParameter("device", device)
                    .setParameter("stDate", device.getStartUse())
                    .setParameter("edDate", end)
                    .setParameter("gluc", glucose)
                    .list();

//                .get(Device.class, device.getId())
//                .getSugarTestMVC().stream()
//                .filter(sugarTestsMVC -> sugarTestsMVC.getAnalysisTime().after(device.getStartUse()))
//                .filter(sugarTestsMVC -> sugarTestsMVC.getAnalysisTime().before(endDate))
//                .collect(Collectors.toList());
    }

    public SugarTestsMVC findLastUserTest(Users user) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, -1);
        Date dateBeforeOneDays = cal.getTime();

        log.info("current -1 day date = " + dateBeforeOneDays);

        return sessionFactory.getCurrentSession()
                .createQuery("from sugartestsmvc s where s.device in " +
                        "(from Device d where d.user = :user) " +
                        "and s.analysisTime > :beforeOneDay order by analysisTime desc", SugarTestsMVC.class)
                .setParameter("user", user)
                .setDate("beforeOneDay", dateBeforeOneDays)
                .setMaxResults(1)
                .uniqueResult();
    }

    public String getUserChartTests(String userName, Double i, Double v) {
        return sessionFactory.getCurrentSession()
                .createQuery("select count(*) from sugartestsmvc s where s.device in " +
                        "(from Device d where d.user = (" +
                        "from Users u where u.login = :uName)) " +
                        "and s.glucose between :i and :v")
                .setParameter("uName", userName)
                .setParameter("i", i)
                .setParameter("v", v)
                .uniqueResult()
                .toString();
    }
}
