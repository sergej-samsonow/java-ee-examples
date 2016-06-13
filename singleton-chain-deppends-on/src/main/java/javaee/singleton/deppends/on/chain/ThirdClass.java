package javaee.singleton.deppends.on.chain;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;

@Singleton
public class ThirdClass {

    private LocalDateTime created;

    @PostConstruct
    private void init() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException exception) {
            throw new RuntimeException(exception);
        }
        created = LocalDateTime.now();
    }

    public Map<String, String> created() {
        HashMap<String, String> result = new HashMap<>();
        result.put(getClass().getName(), " MINUTE " + created.getMinute() + " SECOND " +  created.getSecond());
        return result;
    }
}
