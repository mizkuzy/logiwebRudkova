package ru.tsystems.logiweb.service.API;

import java.util.List;

public interface GenericService<E, K> {

    /**
     * There are CRUD operations from services + getAllSpecialEntities operation.
     */

    /**
     * Create required entity.
     *
     * @param entity
     */
    void create(E entity);

    /**
     * Read required entity.
     *
     * @param id
     * @return entity
     */
    E read(K id);

    /**
     * Update required entity.
     *
     * @param entity
     */
    void update(E entity);

    /**
     * Delete required entity.
     *
     * @param entity
     */
    void delete(E entity);

    /**
     * Get list of required entities.
     *
     * @return list of entities
     */
    List getAll();
}
