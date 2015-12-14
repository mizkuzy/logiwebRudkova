package ru.tsystems.logiweb.dao.IMPL;

import ru.tsystems.logiweb.dao.API.VanGenericDAO;
import ru.tsystems.logiweb.entities.Van;
import org.springframework.stereotype.Repository;

/**
 * An implementation of VanGenericDAO API.
 */
@Repository("vanDAO")
public class VanGenericDAOImpl extends GenericDAOImpl<Van, Integer> implements VanGenericDAO {

    @Override
    public Van getByNumber(String name) {
        return null;
    }//TODO не сделано
}
