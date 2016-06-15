package javaee.examples.test.jpa.layer.utils;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaProvider {
    private static final String JDBC = "jdbc:h2:mem:application_db";
    private static final String USER = "sa";
    private static final String PASS = "";
    private static JpaProvider instance;

    private EntityManagerFactory factory;

    public static JpaProvider instance() {
        if (instance == null) {
            Map<String, String> properties = new HashMap<>();
            properties.put("javax.persistence.jdbc.url", JDBC);
            properties.put("javax.persistence.jdbc.user", USER);
            properties.put("javax.persistence.jdbc.password", PASS);
            properties.put("javax.persistence.transactionType", "RESOURCE_LOCAL");
            properties.put("javax.persistence.provider", "org.hibernate.jpa.HibernatePersistenceProvider");
            properties.put("javax.persistence.jtaDataSource", null);

            properties.put("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
            properties.put("hibernate.archive.autodetection", "class");
            instance = new JpaProvider();
            instance.factory = Persistence.createEntityManagerFactory("application-ds", properties);
        }
        return instance;
    }

    public EntityManagerFactory getFactory() {
        return factory;
    }

    public EntityManager getEntityManager() {
        return factory.createEntityManager();
    }

}
