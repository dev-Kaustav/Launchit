package com.kaustav.launchit.db;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class SkuRepository implements CrudRepository<Sku> {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Sku sku) {
        em.persist(sku);
        em.flush();
    }

    @Override
    public Sku read(int id) {
        return em.find(Sku.class, id);
    }

    @Override
    public void update(Sku sku) {
        em.merge(sku);
    }

    @Override
    public void delete(int id) {
        Sku s = em.find(Sku.class, id);
        if (s != null) {
            em.remove(s);
        }
    }
}
