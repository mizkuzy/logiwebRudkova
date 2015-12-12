package service.IMPL;

import dao.API.RoutGenericDAO;
import dao.IMPL.RoutDAOImpl;
import entities.Rout;
import service.API.RoutService;

import java.util.List;

public class RoutServiceImpl implements RoutService {

    private RoutGenericDAO routDAO = new RoutDAOImpl();

    @Override
    public void create(Rout entity) {
        routDAO.create(entity);
    }

    @Override
    public Rout read(Integer id) {
        return routDAO.read(id);
    }

    @Override
    public void update(Rout entity) {
        routDAO.update(entity);
    }

    @Override
    public void delete(Rout entity) {
        routDAO.delete(entity);
    }

    @Override
    public List getAll() {
        return routDAO.getAll();
    }
}
