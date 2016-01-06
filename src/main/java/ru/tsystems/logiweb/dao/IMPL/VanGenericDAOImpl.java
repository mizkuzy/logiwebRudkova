package ru.tsystems.logiweb.dao.IMPL;

import ru.tsystems.logiweb.dao.API.VanDAO;
import ru.tsystems.logiweb.entities.Van;
import org.springframework.stereotype.Repository;

/**
 * An implementation of VanDAO API.
 */
@Repository("vanDAO")
public class VanGenericDAOImpl extends GenericDAOImpl<Van, Integer> implements VanDAO {

}
