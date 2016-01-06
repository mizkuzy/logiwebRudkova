package ru.tsystems.logiweb.dao.API;

import ru.tsystems.logiweb.entities.Rout;

public interface RoutGenericDAO extends GenericDAO<Rout, Integer> {

    Rout getByCities(String city1, String city2);
}
