package javaee.singleton.chain;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.ejb.Singleton;
import javax.inject.Inject;

@Singleton
public class SecondClass {

    @Inject
    private ThirdClass third;
    private AtomicInteger counter = new AtomicInteger(0);

    public List<String> call(List<String> parameters) {
        parameters.add(getClass().getName() + " : " + counter.incrementAndGet());
        return third.call(parameters);
    }
}
