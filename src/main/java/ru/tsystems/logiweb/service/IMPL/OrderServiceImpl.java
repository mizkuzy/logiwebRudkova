package ru.tsystems.logiweb.service.IMPL;

import org.apache.log4j.Logger;
import ru.tsystems.logiweb.dao.API.OrderGenericDAO;
import ru.tsystems.logiweb.entities.Driver;
import ru.tsystems.logiweb.entities.Order;
import ru.tsystems.logiweb.entities.Request;
import ru.tsystems.logiweb.entities.Van;
import ru.tsystems.logiweb.entities.statusesAndStates.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.logiweb.service.API.DriverService;
import ru.tsystems.logiweb.service.API.OrderService;
import ru.tsystems.logiweb.service.API.RequestService;

import java.util.ArrayList;
import java.util.List;

/**
 * An implementation of OrderService API.
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    private Logger logger = Logger.getLogger(OrderServiceImpl.class);
    @Autowired
    private RequestService requestService;
    @Autowired
    DriverService driverService;
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
     * Get list of required ru.tsystems.logiweb.entities.
     *
     * @return list of ru.tsystems.logiweb.entities
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
     *
     * @param number
     * @return Order's instance. //TODO Герман. Можно ли здесь употребить instance?
     */
    @Override
    public Order getByNumber(Integer number) {
        return orderDAO.getByNumber(number);
    }

    /**
     * Creates order with new parameters: status, number, lists of requests
     *
     * @param requests
     * @return new order
     */
    @Override
    @Transactional
    public Order addNewOrder(List<Request> requests) {
        Order order = new Order();
        order.setStatus(OrderStatus.PROCESS);
        create(order);
        logger.debug("order " + order.getIdOrder() + " is created");
        Integer orderNumber = order.getIdOrder();
        order.setNumber(orderNumber);
        for (Request r : requests) {
            order.addRequest(r);
            //TODO Герман. почему order не добавляется в request? Через дебаг я смотрела - нулов нет) но в БД не появляется(
            r.setCurrentOrder(order);
            requestService.update(r);
        }
        order.setStatus(OrderStatus.PROCESS);
        update(order);
        logger.debug("order " + order.getIdOrder() + " is updated");
        return order;
    }

    /**
     * Counts whole mass of order by request
     *
     * @param requests
     * @return order mass
     */
    @Override
    public int countOrderMass(List<Request> requests) {
        int mass = 0;
        for (Request r :
                requests) {
            mass += r.getGoodForRequest().getMass();
        }
        return mass;
    }

    @Override
    @Transactional
    public void setVanToOrder(Van van, Order order) {
        order.setVan(van);
        update(order);
    }

    @Override
    @Transactional
    public void setDriversToOrder(List<Driver> selectedDrivers, Order order) {
        for (Driver d : selectedDrivers) {
            d.setCurrentOrder(order);
            order.addDriver(d);
            driverService.update(d);
        }
        update(order);
    }
}
