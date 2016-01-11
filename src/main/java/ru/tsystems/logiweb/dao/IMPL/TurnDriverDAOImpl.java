package ru.tsystems.logiweb.dao.IMPL;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import ru.tsystems.logiweb.dao.API.TurnDriverDAO;
import ru.tsystems.logiweb.entities.Driver;
import ru.tsystems.logiweb.entities.TurnDriver;
import ru.tsystems.logiweb.exceptions.CustomLogiwebException;

import javax.persistence.*;

/**
 * An implementation of TurnDriverDAO API.
 */
@Repository("TurnDriverDAO")
public class TurnDriverDAOImpl extends GenericDAOImpl<TurnDriver, Integer> implements TurnDriverDAO {

    private Logger logger = Logger.getLogger(TurnDriverDAOImpl.class);

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

    /**
     * {@inheritDoc}
     * @param driverNumber
     * @return
     * @throws CustomLogiwebException
     */
    @Override
    public TurnDriver getTurnDriverByDriverNumber(Integer driverNumber) throws CustomLogiwebException{
        try {
            Query query = entityManager.createQuery("select turn from TurnDriver turn where turn.driverNumber=:driverNumber");
            query.setParameter("driverNumber", driverNumber);
            return (TurnDriver) query.getSingleResult();
        } catch (PersistenceException ex) {
            logger.info("There is no required turnDriver with driverNumber " + driverNumber, ex);

            throw new CustomLogiwebException("Entity with e-driverNumber: " + driverNumber + " not found ", ex.getMessage());
        }
    }
}