package dao.IMPL;

import dao.API.DriverGenericDAO;
import entities.Driver;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * An implementation of DriverGenericDAO API.
 */
@Repository("driverDAO")
public class DriverGenericDAOImpl extends GenericDAOImpl<Driver, Integer> implements DriverGenericDAO {


    @PersistenceContext
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
