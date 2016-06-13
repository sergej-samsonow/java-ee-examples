package javaee.singleton.chain;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.ejb.DependsOn;
import javax.ejb.Singleton;
import javax.inject.Inject;

@Singleton
public class FirstClass {

    @Inject
    private SecondClass second;
    private AtomicInteger counter = new AtomicInteger(0);

    public List<String> call(List<String> parameters) {
        parameters.add(getClass().getName() + " : " + counter.incrementAndGet());
        if (parameters.size() < 10) {
            second.call(parameters);
        }
        return parameters;
    } 
}
