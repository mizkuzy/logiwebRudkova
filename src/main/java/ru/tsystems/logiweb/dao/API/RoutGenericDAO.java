package ru.tsystems.logiweb.dao.API;

import ru.tsystems.logiweb.entities.Rout;
import ru.tsystems.logiweb.exceptions.CustomLogiwebException;

import java.util.List;

public interface RoutGenericDAO extends GenericDAO<Rout, Integer> {

    Rout getByCities(String city1, String city2) throws CustomLogiwebException;

    /**
     * Makes query to DB to get set of cities corresponding cityFrom.
     *
     * @param cityFrom
     * @return set of cities
     */
    List<String> getCorrespondingCitiesForCityFrom(String cityFrom);

}
