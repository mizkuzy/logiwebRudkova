package service.IMPL;

import dao.API.RoutLabelGenericDAO;
import dao.IMPL.RoutLabelGenericDAOImpl;
import entities.RouteLabel;
import service.API.RoutLabelService;

import java.util.List;

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
