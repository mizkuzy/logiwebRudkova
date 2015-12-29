package ru.tsystems.logiweb.service.IMPL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.logiweb.dao.API.RoutLabelGenericDAO;
import ru.tsystems.logiweb.entities.RouteLabel;
import ru.tsystems.logiweb.service.API.RoutLabelService;

import java.util.List;

/**
 * An implementation of RoutLabelService API.
 */
@Service("routLabelService")
public class RoutLabelServiceImpl implements RoutLabelService {

    @Autowired
    RoutLabelGenericDAO routLabelDAO;

    /**
     * Create required entity.
     *
     * @param entity
     */
    @Override
    @Transactional
    public void create(RouteLabel entity) {
        routLabelDAO.create(entity);
    }

    /**
     * Read required entity.
     *
     * @param id
     * @return
     */
    @Override
    @Transactional
    public RouteLabel read(Integer id) {
        return routLabelDAO.read(id);
    }


    /**
     * Update required entity.
     *
     * @param entity
     */
    @Override
    @Transactional
    public void update(RouteLabel entity) {
        routLabelDAO.update(entity);
    }

    /**
     * Delete required entity.
     *
     * @param entity
     */
    @Override
    @Transactional
    public void delete(RouteLabel entity) {
        routLabelDAO.delete(entity);
    }


    /**
     * Get list of required entities.
     *
     * @return list of entities
     */
    @Override
    @Transactional
    public List getAll() {
        return routLabelDAO.getAll();
    }

    /**
     * Get rout label by name.
     *
     * @param routLabel
     * @return rout label
     */
    @Override
    public RouteLabel getByName(String routLabel) {
        return routLabelDAO.getByName(routLabel);
    }

}
