package dao.IMPL;

import dao.API.RoutGenericDAO;
import entities.Rout;

/**
 * An implementation of RoutDAO API.
 */
// TODO раскомментировать когда спринг сконфигурю. @Repository("routDAO")
public class RoutDAOImpl extends GenericDAOImpl<Rout, Integer> implements RoutGenericDAO {

    @Override
    public Rout getByCities(String city1, String city2) {
        return null;
    } //TODO не сделано
}
