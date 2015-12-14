package service.IMPL;

import dao.API.RoutGenericDAO;
import dao.IMPL.RoutDAOImpl;
import entities.Rout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.API.RoutService;

import java.util.List;

/**
 * An implementation of RoutService API.
 */
@Service("routService")
public class RoutServiceImpl implements RoutService {

    @Autowired
    private RoutGenericDAO routDAO;

    /**
     * Create required entity.
     *
     * @param entity
     */
    @Override
    @Transactional
    public void create(Rout entity) {
        routDAO.create(entity);
    }

    /**
     * Read required entity.
     *
     * @param id
     * @return
     */
    @Override
    @Transactional
    public Rout read(Integer id) {
        return routDAO.read(id);
    }

    /**
     * Update required entity.
     *
     * @param entity
     */
    @Override
    @Transactional
    public void update(Rout entity) {
        routDAO.update(entity);
    }

    /**
     * Delete required entity.
     *
     * @param entity
     */
    @Override
    @Transactional
    public void delete(Rout entity) {
        routDAO.delete(entity);
    }

    /**
     * Get list of required entities.
     *
     * @return list of entities
     */
    @Override
    @Transactional
    public List getAll() {
        return routDAO.getAll();
    }
}
