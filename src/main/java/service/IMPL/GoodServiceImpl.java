package service.IMPL;

import dao.API.GoodGenericDAO;
import dao.IMPL.GoodGenericDAOImpl;
import entities.Good;
import service.API.GoodService;

import java.util.List;

public class GoodServiceImpl implements GoodService {

    private GoodGenericDAO goodDAO = new GoodGenericDAOImpl();

    @Override
    public void create(Good entity) {
        goodDAO.create(entity);
    }

    @Override
    public Good read(Integer id) {
        return goodDAO.read(id);
    }

    @Override
    public void update(Good entity) {
        goodDAO.update(entity);
    }

    @Override
    public void delete(Good entity) {
        goodDAO.delete(entity);
    }

    @Override
    public List getAll() {
        return goodDAO.getAll();
    }
}
