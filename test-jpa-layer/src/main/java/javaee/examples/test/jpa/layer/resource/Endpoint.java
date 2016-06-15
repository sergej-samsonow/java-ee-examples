package javaee.examples.test.jpa.layer.resource;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import javaee.examples.test.jpa.layer.dao.UserDao;
import javaee.examples.test.jpa.layer.entity.User;

@Startup
@Path("adults")
@Singleton
@Produces(MediaType.APPLICATION_JSON)
public class Endpoint {


    @Inject
    private UserDao dao;

    @PostConstruct
    private void init() {
        User maik = new User();
        maik.setName("Maik");
        maik.setAge(30);
        dao.store(maik);

        User bob = new User();
        bob.setName("Bob");
        bob.setAge(28);
        dao.store(bob);
    }

    @GET
    @Lock(LockType.READ)
    public List<User> adults() {
        return dao.getAdultUsers();
    }

}
