package javaee.examples.test.jpa.layer.dao;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;

import javaee.examples.test.jpa.layer.entity.User;
import javaee.examples.test.jpa.layer.utils.JpaProvider;

public class UserDaoTest {

    @InjectMocks
    private UserDao dao;

    @Spy
    private EntityManager em;

    @Before
    public void prepare() {
        em = JpaProvider.instance().getEntityManager();
        initMocks(this);
    }

    @After
    public void cleanup() {
        em.getTransaction().begin();
        em.createQuery("delete from User").executeUpdate();
        em.getTransaction().commit();
    }

    @Test
    public void testStore() throws Exception {
        User user = new User();
        user.setName("John");
        user.setAge(30);
        
        em.getTransaction().begin();
        dao.store(user);
        em.getTransaction().commit();
        
        User stored = em.createQuery("from User as u where u.name = 'John'", User.class).getSingleResult();
        assertThat(stored.getAge(), equalTo(30));
        assertThat(stored.getId(), notNullValue());
    }

    @Test
    public void testGetAdultUsers() throws Exception {
        em.getTransaction().begin();
        User ann = new User();
        ann.setName("Ann");
        ann.setAge(30);

        User bob = new User();
        bob.setName("Bob");
        bob.setAge(8);
        em.persist(ann);
        em.persist(bob);
        em.getTransaction().commit();

        em.getTransaction().begin();
        List<User> adults = dao.getAdultUsers();
        em.getTransaction().commit();
        assertThat(adults, equalTo(asList(ann)));
    }

    
}
