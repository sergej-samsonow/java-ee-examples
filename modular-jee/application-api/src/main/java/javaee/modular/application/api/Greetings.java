package javaee.modular.application.api;

import javax.ejb.Local;

@Local
public interface Greetings {

    public GreetingsMessage greet(User user);

}
