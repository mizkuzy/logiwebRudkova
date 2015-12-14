package ru.tsystems.logiweb.dao.IMPL;

import ru.tsystems.logiweb.dao.API.GoodGenericDAO;
import ru.tsystems.logiweb.entities.Good;
import org.springframework.stereotype.Repository;

/**
 * An implementation of GoodGenericDAO API.
 */
@Repository("goodDAO")
public class GoodGenericDAOImpl extends GenericDAOImpl<Good,Integer> implements GoodGenericDAO {
}
