package ru.tsystems.logiweb.service.IMPL;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tsystems.logiweb.dao.API.TurnDriverDAO;
import ru.tsystems.logiweb.entities.Driver;
import ru.tsystems.logiweb.entities.TurnDriver;
import ru.tsystems.logiweb.service.API.TurnDriverService;

/**
 * An implementation of TurnDriver API.
 */
@Service("turnDriverService")
public class TurnDriverServiceImpl implements TurnDriverService {

    private Logger logger = Logger.getLogger(TurnDriverServiceImpl.class);

    @Autowired
    private TurnDriverDAO turnDriverDAO;

    @Override
    public TurnDriver getTurnDriverByDriverNumber(Driver driverNumber) {
        return turnDriverDAO.getTurnDriverByDriverNumber(driverNumber);
    }
}
