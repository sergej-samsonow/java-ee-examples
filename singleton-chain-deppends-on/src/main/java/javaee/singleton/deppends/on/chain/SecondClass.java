package javaee.singleton.deppends.on.chain;

import java.time.LocalDateTime;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.DependsOn;
import javax.ejb.Singleton;
import javax.inject.Inject;

@Singleton
@DependsOn("ThirdClass")
public class SecondClass {

    @Inject
    private ThirdClass third;
    private LocalDateTime created;

    @PostConstruct
    private void prepare() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException exception) {
            new RuntimeException(exception);
        }
        created = LocalDateTime.now();
    }

    public Map<String,String> created() {
        Map<String, String> result = third.created();
        result.put(getClass().getName(), " MINUTE " + created.getMinute() + " SECOND " +  created.getSecond());
        return result;
    }

}
