package javaee.modular.application;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.naming.InitialContext;
import javaee.modular.application.api.Greetings;
import javaee.modular.application.api.GreetingsMessage;
import javaee.modular.application.api.User;

@Stateful
public class ModularGreetings {

    /*
     * Example CDI module reference, referenced EJB is located in api deployment unit.
     */
    @EJB(lookup = "java:global/application-api/StandardGreetings!javaee.modular.application.api.Greetings")
    private Greetings fallback;

    @Inject
    private RequestContext context;

    private String name;

    private String module;

    public void setName(String name) {
        this.name = name;
    }
    public void setModule(String module) {
        this.module = module;
    }
    public GreetingsMessage greeting() {
        if (name == null) {
            context.addMessage("Name is missing replace with 'anonymous'");
            this.name = "anonymous";
        }
        User user = new User(name);
        if (module != null) {
            try {
                /*
                 * Runtime module load from deployment units passed over with 'module' parameter. Referenced modules
                 * can be deployed / undeployed at runtime this deployment unit don't care about. Please pay attention
                 * you have to filter 'module' parameter, this example is just a technology demostration and 
                 * not a production code, using unfiltered and unchecked user input is highly insecure!
                 */
                context.addMessage("Load dynamic module: " + module);
                InitialContext ctx = new InitialContext();
                Greetings greetings = (Greetings) ctx.lookup("java:global/" + module + "!javaee.modular.application.api.Greetings");
                return greetings.greet(user);
            }
            catch (Exception e) {
                context.addMessage("Switch to fallback exception on module load: " + module + " message: " + e.getMessage());
            }
        }
        return fallback.greet(user);
    }
}
