package ru.tsystems.logiweb.dao.IMPL;

import ru.tsystems.logiweb.dao.API.DriverGenericDAO;
import ru.tsystems.logiweb.entities.Driver;
import org.springframework.stereotype.Repository;
import ru.tsystems.logiweb.entities.Order;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import java.util.List;

/**
 * An implementation of DriverGenericDAO API.
 */
@Repository("driverDAO")
public class DriverDAOImpl extends GenericDAOImpl<Driver, Integer> implements DriverGenericDAO {

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

}
