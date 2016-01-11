package ru.tsystems.logiweb.dao.IMPL;

import org.apache.log4j.Logger;
import ru.tsystems.logiweb.dao.API.RoutGenericDAO;
import ru.tsystems.logiweb.entities.Rout;
import org.springframework.stereotype.Repository;
import ru.tsystems.logiweb.exceptions.CustomLogiwebException;

import javax.persistence.*;

/**
 * An implementation of RoutDAO API.
 */
@Repository("routDAO")
public class RoutDAOImpl extends GenericDAOImpl<Rout, Integer> implements RoutGenericDAO {

    private Logger logger = Logger.getLogger(RoutDAOImpl.class);

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

    /**
     * Gets required rout.
     *
     * @param city1
     * @param city2
     * @return rout
     * @throws PersistenceException
     */
    @Override
    public Rout getByCities(String city1, String city2) throws CustomLogiwebException {
        try {
            Query query = entityManager.createQuery("select rout from Rout rout where (rout.city1=:city1 and rout.city2=:city2)");

            query.setParameter("city1", city1);
            query.setParameter("city2", city2);

            return (Rout) query.getSingleResult();

        } catch (PersistenceException ex) {

            logger.info(ex.getMessage() + "Wrong rout: city1 - " + city1 + "; city2 - " + city2, ex);
            throw new CustomLogiwebException("Rout with parameters: city1 - " + city1 + "; city2 - " + city2+" not found.",ex.getMessage());
        }
    }
}