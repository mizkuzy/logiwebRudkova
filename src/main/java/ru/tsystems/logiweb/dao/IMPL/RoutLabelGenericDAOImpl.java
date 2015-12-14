package ru.tsystems.logiweb.dao.IMPL;

import ru.tsystems.logiweb.dao.API.RoutLabelGenericDAO;
import ru.tsystems.logiweb.entities.RouteLabel;
import org.springframework.stereotype.Repository;

/**
 * An implementation of RoutLabelGenericDAO API.
 */
@Repository("routLabelDAO")
public class RoutLabelGenericDAOImpl extends GenericDAOImpl<RouteLabel, Integer> implements RoutLabelGenericDAO {

}
