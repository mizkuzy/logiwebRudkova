package ru.tsystems.logiweb.dao.IMPL;

import ru.tsystems.logiweb.dao.API.DriverGenericDAO;
import ru.tsystems.logiweb.entities.Driver;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

/**
 * An implementation of DriverGenericDAO API.
 */
@Repository("driverDAO")
public class DriverDAOImpl extends GenericDAOImpl<Driver, Integer> implements DriverGenericDAO {

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

    /**
     * Get required DriverEntity by specified name and surname
     * @param name
     * @param surname
     * @return Driver's instance. //TODO Герман. Можно ли здесь употребить instance?
     */
    @Override
    public Driver getByNameAndSurname(String name, String surname) {
        return null;
    } //TODO не сделано
}
