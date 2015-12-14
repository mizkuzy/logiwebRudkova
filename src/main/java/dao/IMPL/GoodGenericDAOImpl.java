package dao.IMPL;

import dao.API.GoodGenericDAO;
import entities.Good;
import org.springframework.stereotype.Repository;

/**
 * An implementation of GoodGenericDAO API.
 */
@Repository("goodDAO")
public class GoodGenericDAOImpl extends GenericDAOImpl<Good,Integer> implements GoodGenericDAO {
}
