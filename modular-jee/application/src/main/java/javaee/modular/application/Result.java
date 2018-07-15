package javaee.modular.application;

import java.util.Collection;
import java.util.Collections;

public class Result {

    private Status status;
    private Object data;
    private Collection<String> messages;

    public Result(Status status, Object data, Collection<String> messages) {
        super();
        this.status = status;
        this.data = data;
        this.messages = messages == null ? Collections.emptyList() : messages;
    }

    public Status getStatus() {
        return status;
    }

    public Object getData() {
        return data;
    }

    public Collection<String> getMessages() {
        return messages;
    }

}
