package javaee.singleton.chain;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("example")
@Produces(MediaType.APPLICATION_JSON)
public class Example {

    @Inject
    private FirstClass first;

    @GET
    public List<String> get() {
        return first.call(new CopyOnWriteArrayList<String>());
    }

}
