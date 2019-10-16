package iot.medicine.sugar;

import iot.medicine.pojo.SugarTests;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SugarReposytory {

    @Autowired
    SessionFactory sessionFactory;

    public List<SugarTests> findTests(Long lastId) {

        return sessionFactory.getCurrentSession()
                .createQuery("from SugarTests sa WHERE sa.id > :param1", SugarTests.class)
                .setParameter("param1", lastId)
                .setMaxResults(20)
                .list();

    }
}
