package iot.medicine.user;

import my.entity.mvc.user.RoleName;
import my.entity.mvc.user.Roles;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Repository
public class RoleRepository {

    private static Logger log = Logger.getLogger("RoleRepository");

    @Autowired
    SessionFactory sessionFactory;

    public Roles findRoleByName(RoleName roleName) {
        try {
            return sessionFactory.getCurrentSession()
                    .createQuery("from Roles where roleName like :param1", Roles.class)
                    .setParameter("param1", roleName)
                    .getSingleResult();
        } catch (HibernateException e) {
            log.warning(e.getMessage());
            return null;
        }
    }

    public List<String> findRoleNames() {
        return sessionFactory.getCurrentSession()
                .createQuery("select roleName from Roles", RoleName.class)
                .list()
                .stream()
                .map(Enum::name)
                .collect(Collectors.toList());
    }


}
