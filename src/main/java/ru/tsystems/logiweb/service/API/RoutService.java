package ru.tsystems.logiweb.service.API;

import ru.tsystems.logiweb.entities.Rout;

import javax.persistence.PersistenceException;

public interface RoutService extends GenericService<Rout, Integer> {

    Rout getByCities(String city1, String city2) throws PersistenceException;

}
