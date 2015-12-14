package dao.IMPL;

import dao.API.RoutLabelGenericDAO;
import entities.RouteLabel;
import org.springframework.stereotype.Repository;

/**
 * An implementation of RoutLabelGenericDAO API.
 */
@Repository("routLabelDAO")
public class RoutLabelGenericDAOImpl extends GenericDAOImpl<RouteLabel, Integer> implements RoutLabelGenericDAO {

}
