package dao.IMPL;

import dao.API.OrderGenericDAO;
import entities.Order;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * An implementation of OrderGenericDAO API.
 */
@Repository("orderDAO")
public class OrderGenericDAOImpl extends GenericDAOImpl<Order, Integer> implements OrderGenericDAO {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Get required OrderEntity by specified number
     * @param number
     * @return Order's instance. //TODO Герман. Можно ли здесь употребить instance?
     */
    @Override
    public Order getByNumber(Integer number) {
        Query query = entityManager.createQuery("SELECT c FROM Order c WHERE c.number=:number");
        query.setParameter("number",number);
        return (Order) query.getSingleResult();
    }

}
