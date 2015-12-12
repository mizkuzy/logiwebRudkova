package service.API;

import java.util.List;

public interface GenericService<E, K> {

    /**
     * There are CRUD operations from services + getAllSpecialEntities operation.
     */

    void create(E entity);

    E read(K id);

    void update(E entity);

    void delete(E entity);

    List getAll();
}
