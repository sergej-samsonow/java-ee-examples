package javaee.modular.application.api;

import javax.ejb.Stateless;

@Stateless
public class StandardGreetings implements Greetings {

    @Override
    public GreetingsMessage greet(User user) {
        return new GreetingsMessage(user, String.format("Hallo %s", user.getName()));
    }
}
