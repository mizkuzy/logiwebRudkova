package dao.IMPL;

import dao.API.RoutGenericDAO;
import entities.Rout;
import org.springframework.stereotype.Repository;

/**
 * An implementation of RoutDAO API.
 */
@Repository("routDAO")
public class RoutDAOImpl extends GenericDAOImpl<Rout, Integer> implements RoutGenericDAO {

    @Override
    public Rout getByCities(String city1, String city2) {
        return null;
    } //TODO не сделано
}
