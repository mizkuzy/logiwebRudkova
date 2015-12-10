package dao.API;

import entities.Rout;

public interface RoutGenericDAO extends GenericDAO<Rout, Integer> {

    Rout getByCities(String city1, String city2); //TODO не сделано
}
