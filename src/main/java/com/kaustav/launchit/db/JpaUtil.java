package com.kaustav.launchit.db;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

/** Utility for obtaining JPA EntityManagers using environment variables. */
public final class JpaUtil {
    private static final EntityManagerFactory emf = buildFactory();

    private JpaUtil() {}

    private static EntityManagerFactory buildFactory() {
        Map<String, String> props = new HashMap<>();
        String url = System.getenv("DB_URL");
        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");
        if (url != null) props.put("jakarta.persistence.jdbc.url", url);
        if (user != null) props.put("jakarta.persistence.jdbc.user", user);
        if (password != null) props.put("jakarta.persistence.jdbc.password", password);
        props.put("jakarta.persistence.jdbc.driver", "org.postgresql.Driver");
        return Persistence.createEntityManagerFactory("launchit", props);
    }

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
