package ru.tsystems.logiweb.service.IMPL;

import ru.tsystems.logiweb.dao.API.RoutLabelGenericDAO;
import ru.tsystems.logiweb.dao.IMPL.RoutLabelGenericDAOImpl;
import ru.tsystems.logiweb.entities.RouteLabel;
import ru.tsystems.logiweb.service.API.RoutLabelService;

import java.util.List;

/**
 * An implementation of RoutLabelService API.
 */
public class RoutLabelServiceImpl implements RoutLabelService {
    RoutLabelGenericDAO routLabelDAO = new RoutLabelGenericDAOImpl();

    @Override
    public void create(RouteLabel entity) {
        routLabelDAO.create(entity);
    }

    @Override
    public RouteLabel read(Integer id) {
        return routLabelDAO.read(id);
    }


    @Override
    public void update(RouteLabel entity) {
        routLabelDAO.update(entity);
    }

    @Override
    public void delete(RouteLabel entity) {
        routLabelDAO.delete(entity);
    }


    @Override
    public List getAll() {
        return routLabelDAO.getAll();
    }
}
