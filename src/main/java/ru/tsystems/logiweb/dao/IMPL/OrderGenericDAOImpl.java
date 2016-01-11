package ru.tsystems.logiweb.dao.IMPL;

import org.apache.log4j.Logger;
import ru.tsystems.logiweb.dao.API.OrderGenericDAO;
import ru.tsystems.logiweb.entities.Order;
import org.springframework.stereotype.Repository;
import ru.tsystems.logiweb.exceptions.CustomLogiwebException;

import javax.persistence.*;

/**
 * An implementation of OrderGenericDAO API.
 */
@Repository("orderDAO")
public class OrderGenericDAOImpl extends GenericDAOImpl<Order, Integer> implements OrderGenericDAO {

    private Logger logger = Logger.getLogger(OrderGenericDAOImpl.class);

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

    /**
     * Get required OrderEntity by specified number
     *
     * @param number
     * @return Order's instance.
     */
    @Override
    public Order getByNumber(Integer number) throws CustomLogiwebException{
        try {
            Query query = entityManager.createQuery("SELECT c FROM Order c WHERE c.number=:number");
            query.setParameter("number", number);
            return (Order) query.getSingleResult();
        } catch (NoResultException e) {
            logger.info("Entity with number " + number + " not found. Exception: ", e);
          throw   new CustomLogiwebException(e.toString(), e.getMessage());
        }
    }

}
