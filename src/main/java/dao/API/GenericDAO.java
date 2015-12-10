package dao.API;

import java.util.List;

public interface GenericDAO<E, K> {

    /**
     * There are CRUD operations +  getAll operation.
     */

    void create(E entity);

    E read(K id);

    void update(E entity);

    void delete(E entity);

    List<E> getAll();
}
