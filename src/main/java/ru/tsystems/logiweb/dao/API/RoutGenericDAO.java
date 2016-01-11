package ru.tsystems.logiweb.dao.API;

import ru.tsystems.logiweb.entities.Rout;
import ru.tsystems.logiweb.exceptions.CustomLogiwebException;

public interface RoutGenericDAO extends GenericDAO<Rout, Integer> {

    Rout getByCities(String city1, String city2) throws CustomLogiwebException;
}
