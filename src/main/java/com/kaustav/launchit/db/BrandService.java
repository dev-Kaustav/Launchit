package com.kaustav.launchit.db;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

/**
 * Service layer for {@link Brand} entity CRUD operations.
 */
@Service
public class BrandService {
    private static final Logger log = LoggerFactory.getLogger(BrandService.class);

    private final BrandRepository repository;

    public BrandService(BrandRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieve all brands from the database.
     */
    public List<Brand> findAll() {
        log.debug("Fetching all brands");
        return repository.findAll();
    }

    /**
     * Find a brand by id.
     *
     * @param id brand id
     * @return the brand or {@code null} if not found
     */
    public Brand find(int id) {
        log.debug("Fetching brand {}", id);
        return repository.findById(id).orElse(null);
    }

    /**
     * Persist a new brand entity.
     */
    public Brand create(Brand brand) {
        log.info("Creating brand {}", brand.getName());
        return repository.save(brand);
    }

    /**
     * Update an existing brand.
     */
    public Brand update(int id, Brand brand) {
        brand.setId(id);
        log.info("Updating brand {}", id);
        return repository.save(brand);
    }

    /**
     * Delete a brand by id.
     */
    public void delete(int id) {
        log.info("Deleting brand {}", id);
        repository.deleteById(id);
    }
}
