package javaee.modular.application;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;


@Path("/")
@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class Application {

    @Inject 
    private RequestContext context;

    @Inject
    private ModularGreetings greetings;

    @GET
    public Result echo(@QueryParam("name") String name, @QueryParam("module") String module) {
        greetings.setName(name);
        greetings.setModule(module);
        return context.result(greetings.greeting());
    }
}
