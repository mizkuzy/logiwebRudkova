package dao.IMPL;

import dao.API.DriverGenericDAO;
import entities.Driver;

/**
 * An implementation of DriverGenericDAO API.
 */
// TODO раскомментировать когда спринг сконфигурю. @Repository("driverDAO")
public class DriverGenericDAOImpl extends GenericDAOImpl<Driver, Integer> implements DriverGenericDAO {

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
