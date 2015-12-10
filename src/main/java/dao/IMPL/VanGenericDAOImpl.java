package dao.IMPL;

import dao.API.VanGenericDAO;
import entities.Van;

/**
 * An implementation of VanGenericDAO API.
 */
// TODO раскомментировать когда спринг сконфигурю. @Repository("vanDAO")
public class VanGenericDAOImpl extends GenericDAOImpl<Van, Integer> implements VanGenericDAO {

    @Override
    public Van getByNumber(String name) {
        return null;
    }//TODO не сделано
}
