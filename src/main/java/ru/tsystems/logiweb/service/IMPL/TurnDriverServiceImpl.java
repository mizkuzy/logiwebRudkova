package ru.tsystems.logiweb.service.IMPL;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.logiweb.dao.API.TurnDriverDAO;
import ru.tsystems.logiweb.entities.Driver;
import ru.tsystems.logiweb.entities.TurnDriver;
import ru.tsystems.logiweb.service.API.TurnDriverService;

import java.util.List;

/**
 * An implementation of TurnDriver API.
 */
@Service("turnDriverService")
public class TurnDriverServiceImpl implements TurnDriverService {

    private Logger logger = Logger.getLogger(TurnDriverServiceImpl.class);

    @Autowired
    private TurnDriverDAO turnDriverDAO;

    /**
     * {@inheritDoc}
     *
     * @param entity
     */
    @Override
    @Transactional
    public void create(TurnDriver entity) {
        turnDriverDAO.create(entity);
    }

    /**
     * {@inheritDoc}
     *
     * @param id
     * @return entity
     */
    @Override
    @Transactional
    public TurnDriver read(Integer id) {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @param entity
     */
    @Override
    @Transactional
    public void update(TurnDriver entity) {

    }

    /**
     * {@inheritDoc}
     *
     * @param entity
     */
    @Override
    @Transactional
    public void delete(TurnDriver entity) {

    }

    @Override
    @Transactional
    public List getAll() {
        return null;
    }

    /**
     * Gets turnDriver entity by driver's number.
     *
     * @param driverNumber
     * @return entity
     */
    @Override
    @Transactional
    public TurnDriver getTurnDriverByDriverNumber(Integer driverNumber) {
        return turnDriverDAO.getTurnDriverByDriverNumber(driverNumber);
    }
}
