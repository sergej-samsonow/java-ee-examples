package javaee.modular.application;

import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.context.RequestScoped;

@Stateful
@RequestScoped
public class RequestContext {

    private Status status;
    private Collection<String> messages;

    @PostConstruct
    public void init() {
        status = Status.OK;
        messages = new ArrayList<>();
    }

    public void addError(String message) {
        status = Status.ERROR;
        addMessage(message);
    }

    public void addMessage(String message) {
        messages.add(message);
    }

    public Result result(Object result) {
        return new Result(status, result, messages);
    }
}
