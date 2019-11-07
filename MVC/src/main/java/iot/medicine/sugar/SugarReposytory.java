package iot.medicine.sugar;

import my.entity.mvc.SugarTests;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SugarReposytory {

    @Autowired
    SessionFactory sessionFactory;

    public List<SugarTests> findTests(int page) {
        int maxResult = 20;
        if (page > 1) page = (page - 1) * 20;
                else page = 0;
        return sessionFactory.getCurrentSession()
                .createQuery("from SugarTests", SugarTests.class)
                .setFirstResult(page)
                .setMaxResults(maxResult)
                .list();

    }
}
