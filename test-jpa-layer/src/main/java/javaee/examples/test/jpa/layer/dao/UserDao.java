package javaee.examples.test.jpa.layer.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import javaee.examples.test.jpa.layer.entity.User;

@Stateless
public class UserDao {

    @PersistenceContext
    private EntityManager em;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void store(User user) {
        em.merge(user);
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<User> getAdultUsers() {
        return em.createNamedQuery("User.select.adults", User.class).getResultList();
    }

}
