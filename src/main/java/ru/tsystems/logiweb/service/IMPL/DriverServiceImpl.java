package ru.tsystems.logiweb.service.IMPL;

import ru.tsystems.logiweb.dao.API.DriverGenericDAO;
import ru.tsystems.logiweb.dao.IMPL.DriverGenericDAOImpl;
import ru.tsystems.logiweb.entities.Driver;
import ru.tsystems.logiweb.service.API.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * An implementation of DriverService API.
 */
@Service("driverService")
public class DriverServiceImpl implements DriverService {

    //TODO ещё лучше разобраться с этой аннотацией
    @Autowired
    private DriverGenericDAO driverDao;

    /**
     * Create required entity.
     *
     * @param entity
     */
    @Override
    @Transactional
    public void create(Driver entity) {
        driverDao.create(entity);
    }

    /**
     * Read required entity.
     *
     * @param id
     * @return
     */
    @Override
    @Transactional
    public Driver read(Integer id) {
        return driverDao.read(id);
    }

    /**
     * Update required entity.
     *
     * @param entity
     */
    @Override
    @Transactional
    public void update(Driver entity) {
        driverDao.update(entity);
    }

    /**
     * Delete required entity.
     *
     * @param entity
     */
    @Override
    @Transactional
    public void delete(Driver entity) {
        driverDao.delete(entity);
    }

    /**
     * Get list of required ru.tsystems.logiweb.entities.
     *
     * @return list of ru.tsystems.logiweb.entities
     */
    @Override
    @Transactional
    public List getAll() {
        return driverDao.getAll();
    }

}
