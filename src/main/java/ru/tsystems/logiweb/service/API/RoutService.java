package ru.tsystems.logiweb.service.API;

import ru.tsystems.logiweb.entities.Rout;

import javax.persistence.PersistenceException;
import java.util.HashSet;
import java.util.List;

public interface RoutService extends GenericService<Rout, Integer> {

    Rout getByCities(String city1, String city2) throws PersistenceException;

    List<String> getCities();

    /**
     * Returns set of cities where we can deliver goods from cityFrom.
     * @param cityFrom
     * @return set of cities
     */
    List<String> getCorrespondingCitiesForCity1(String cityFrom);
}
