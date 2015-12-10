package dao.API;

import entities.Driver;

public interface DriverGenericDAO extends GenericDAO<Driver, Integer>{

    Driver getByNameAndSurname(String name, String surname);
}

