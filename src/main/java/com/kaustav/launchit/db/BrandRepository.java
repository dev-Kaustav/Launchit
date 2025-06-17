package com.kaustav.launchit.db;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class BrandRepository implements CrudRepository<Brand> {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Brand brand) {
        em.persist(brand);
        em.flush();
    }

    @Override
    public Brand read(int id) {
        return em.find(Brand.class, id);
    }

    @Override
    public void update(Brand brand) {
        em.merge(brand);
    }

    @Override
    public void delete(int id) {
        Brand b = em.find(Brand.class, id);
        if (b != null) {
            em.remove(b);
        }
    }
}
