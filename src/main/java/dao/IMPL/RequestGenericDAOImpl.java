package dao.IMPL;

import dao.API.RequestGenericDAO;
import entities.Request;
import org.springframework.stereotype.Repository;

/**
 * An implementation of RequestGenericDAO API.
 */
@Repository("requestDAO")
public class RequestGenericDAOImpl extends GenericDAOImpl<Request, Integer> implements RequestGenericDAO {

}
