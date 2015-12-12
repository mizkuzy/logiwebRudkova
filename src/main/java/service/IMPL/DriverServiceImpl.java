package service.IMPL;

import dao.API.DriverGenericDAO;
import dao.IMPL.DriverGenericDAOImpl;
import entities.Driver;
import service.API.DriverService;

import java.util.List;


public class DriverServiceImpl implements DriverService {

    private DriverGenericDAO driverDao = new DriverGenericDAOImpl();

    @Override
    public void create(Driver entity) {
        driverDao.create(entity);
    }

    @Override
    public Driver read(Integer id) {
        return driverDao.read(id);
    }

    @Override
    public void update(Driver entity) {
        driverDao.update(entity);
    }

    @Override
    public void delete(Driver entity) {
        driverDao.delete(entity);
    }

    @Override
    public List getAll() {
        return driverDao.getAll();
    }

}
