package javaee.singleton.deppends.on.chain;

import java.time.LocalDateTime;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.DependsOn;
import javax.ejb.Singleton;
import javax.inject.Inject;

@Singleton
@DependsOn("SecondClass")
public class FirstClass {

    @Inject
    private SecondClass second;
    private LocalDateTime created;

    @PostConstruct
    private void init() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException exception) {
            new RuntimeException(exception);
        }
        created = LocalDateTime.now();
    }

    public Map<String,String> created() {
        Map<String, String> result = second.created();
        result.put(getClass().getName(), " MINUTE " + created.getMinute() + " SECOND " +  created.getSecond());
        return result;
    }

}
