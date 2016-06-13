package javaee.singleton.deppends.on.chain;

import java.util.Map;

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
    public Map<String, String> get() {
        // this call takes 6 second
        return first.created();
    }

}
