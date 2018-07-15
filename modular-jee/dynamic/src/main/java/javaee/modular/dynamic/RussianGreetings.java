package javaee.modular.dynamic;

import javax.ejb.Stateless;
import javaee.modular.application.api.Greetings;
import javaee.modular.application.api.GreetingsMessage;
import javaee.modular.application.api.User;

@Stateless
public class RussianGreetings implements Greetings {

    @Override
    public GreetingsMessage greet(User user) {
        return new GreetingsMessage(user, "Добрый день " + user.getName());
    }
}
