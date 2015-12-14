package ru.tsystems.logiweb.service.IMPL;

import ru.tsystems.logiweb.dao.API.RoutGenericDAO;
import ru.tsystems.logiweb.entities.Rout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.logiweb.service.API.RoutService;

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
     * Get list of required ru.tsystems.logiweb.entities.
     *
     * @return list of ru.tsystems.logiweb.entities
     */
    @Override
    @Transactional
    public List getAll() {
        return routDAO.getAll();
    }
}
