package ru.tsystems.logiweb.dao.IMPL;

import org.apache.log4j.Logger;
import ru.tsystems.logiweb.dao.API.RoutLabelGenericDAO;
import ru.tsystems.logiweb.entities.RouteLabel;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

/**
 * An implementation of RoutLabelGenericDAO API.
 */
@Repository("routLabelDAO")
public class RoutLabelGenericDAOImpl extends GenericDAOImpl<RouteLabel, Integer> implements RoutLabelGenericDAO {

    private Logger logger = Logger.getLogger(RoutLabelGenericDAOImpl.class);

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

    @Override
    public RouteLabel getByName(String routLabel) {
        try {
            Query query = entityManager.createQuery("select r from RouteLabel r where r.label=:routLabel");
            query.setParameter("routLabel", routLabel);
            return (RouteLabel) query.getSingleResult();
        } catch (PersistenceException ex) {
            logger.info("There was NoResultException because of wrong routLabel: " + routLabel);
            throw new NoResultException("Entity with routLabel " + routLabel + "not found ");
        }

    }
}