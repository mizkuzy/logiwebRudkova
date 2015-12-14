package ru.tsystems.logiweb.dao.IMPL;

import ru.tsystems.logiweb.dao.API.RequestGenericDAO;
import ru.tsystems.logiweb.entities.Request;
import org.springframework.stereotype.Repository;

/**
 * An implementation of RequestGenericDAO API.
 */
@Repository("requestDAO")
public class RequestGenericDAOImpl extends GenericDAOImpl<Request, Integer> implements RequestGenericDAO {

}
