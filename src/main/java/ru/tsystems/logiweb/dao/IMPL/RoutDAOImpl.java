package ru.tsystems.logiweb.dao.IMPL;

import org.apache.log4j.Logger;
import ru.tsystems.logiweb.dao.API.RoutGenericDAO;
import ru.tsystems.logiweb.entities.Rout;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

/**
 * An implementation of RoutDAO API.
 */
@Repository("routDAO")
public class RoutDAOImpl extends GenericDAOImpl<Rout, Integer> implements RoutGenericDAO {

    private Logger logger = Logger.getLogger(RoutDAOImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Rout getByCities(String city1, String city2) throws PersistenceException {
        try {
            Query query = entityManager.createQuery("select rout from Rout rout where (rout.city1=:city1 and rout.city2=:city2) or" +
                    "(rout.city1=:city2 and rout.city2=:city1)");

            query.setParameter("city1", city1);
            query.setParameter("city2", city2);

            return (Rout) query.getSingleResult();

        } catch (PersistenceException ex) {
            logger.error("Rout not found in method getByCities()");

            throw new PersistenceException(ex);
        }
    }
}