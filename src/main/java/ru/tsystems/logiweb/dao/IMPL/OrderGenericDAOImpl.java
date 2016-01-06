package ru.tsystems.logiweb.dao.IMPL;

import ru.tsystems.logiweb.dao.API.OrderGenericDAO;
import ru.tsystems.logiweb.entities.Order;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

/**
 * An implementation of OrderGenericDAO API.
 */
@Repository("orderDAO")
public class OrderGenericDAOImpl extends GenericDAOImpl<Order, Integer> implements OrderGenericDAO {

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

    /**
     * Get required OrderEntity by specified number
     * @param number
     * @return Order's instance.
     */
    @Override
    public Order getByNumber(Integer number) {
        Query query = entityManager.createQuery("SELECT c FROM Order c WHERE c.number=:number");
        query.setParameter("number",number);
        return (Order) query.getSingleResult();
    }

}
