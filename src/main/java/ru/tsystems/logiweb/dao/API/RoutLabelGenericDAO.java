package ru.tsystems.logiweb.dao.API;

import ru.tsystems.logiweb.entities.RouteLabel;
import ru.tsystems.logiweb.exceptions.CustomLogiwebException;

public interface RoutLabelGenericDAO extends GenericDAO<RouteLabel,Integer> {

    RouteLabel getByName(String routLabel) throws CustomLogiwebException;
}
