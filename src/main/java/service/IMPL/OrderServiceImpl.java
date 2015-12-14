package service.IMPL;

import dao.API.OrderGenericDAO;
import dao.IMPL.OrderGenericDAOImpl;
import entities.Order;
import entities.statusesAndStates.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.API.OrderService;

import java.util.ArrayList;
import java.util.List;

/**
 * An implementation of OrderService API.
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderGenericDAO orderDAO;

    /**
     * Create required entity.
     *
     * @param entity
     */
    @Override
    @Transactional
    public void create(Order entity) {
        orderDAO.create(entity);
    }

    /**
     * Read required entity.
     *
     * @param id
     * @return
     */
    @Override
    @Transactional
    public Order read(Integer id) {
        return orderDAO.read(id);
    }

    /**
     * Update required entity.
     *
     * @param entity
     */
    @Override
    @Transactional
    public void update(Order entity) {
        orderDAO.update(entity);
    }

    /**
     * Delete required entity.
     *
     * @param entity
     */
    @Override
    @Transactional
    public void delete(Order entity) {
        orderDAO.delete(entity);
    }

    /**
     * Get list of required entities.
     *
     * @return list of entities
     */
    @Override
    @Transactional
    public List<Order> getAll() {
        return orderDAO.getAll();
    }

    //TODO объединить два нижеследующих метода в один с помощью параметра OrderStatus
    //TODO TEST

    /**
     * Get List of orders with OrderStatus=DONE.
     *
     * @return
     */
    @Override
    public ArrayList<Order> getOrdersDone() {

        List<Order> ordersList = getAll();
        ArrayList<Order> ordersDone = new ArrayList<>();

        if (ordersList != null) {
            for (Order o : ordersList) {
                if (o.getStatus().equals(OrderStatus.DONE)) {
                    ordersDone.add(o);
                }
            }
        }
        return ordersDone;
    }
    /**
     * Get List of orders with OrderStatus=PROCESS.
     *
     * @return ordersProcess
     */
    @Override
    public ArrayList<Order> getOrdersProcess() {
        List<Order> ordersList = getAll();
        ArrayList<Order> ordersProcess = new ArrayList<>();

        if (ordersList != null) {
            for (Order o : ordersList) {
                if (o.getStatus().equals(OrderStatus.PROCESS)) {
                    ordersProcess.add(o);
                }
            }
        }
        return ordersProcess;
    }

    /**
     * Get required OrderEntity by specified number
     * @param number
     * @return Order's instance. //TODO Герман. Можно ли здесь употребить instance?
     */
    @Override
    public Order getByNumber(Integer number) {
        return orderDAO.getByNumber(number);
    }
}
