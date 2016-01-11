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
import ru.tsystems.logiweb.exceptions.CustomLogiwebException;
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
     * Creates required entity.
     *
     * @param entity
     */
    @Override
    @Transactional
    public void create(Order entity) {
        orderDAO.create(entity);
    }

    /**
     * Reads required entity.
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
     * Updates required entity.
     *
     * @param entity
     */
    @Override
    @Transactional
    public void update(Order entity) {
        orderDAO.update(entity);
    }

    /**
     * Deletes required entity.
     *
     * @param entity
     */
    @Override
    @Transactional
    public void delete(Order entity) {
        orderDAO.delete(entity);
    }

    /**
     * Gets list of required entities.
     *
     * @return list of entities
     */
    @Override
    @Transactional
    public List<Order> getAll() {
        return orderDAO.getAll();
    }

    /**
     * Gets List of orders with OrderStatus=DONE.
     *
     * @return list of orders with status DONE
     */
    @Override
    public List<Order> getOrdersDone() {

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
     * Gets List of orders with OrderStatus=PROCESS.
     *
     * @return list of orders with status PROCESS
     */
    @Override
    public List<Order> getOrdersProcess() {
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
     * Gets required OrderEntity by specified number
     *
     * @param number
     * @return Order's instance.
     */
    @Override
    public Order getByNumber(Integer number) throws CustomLogiwebException {
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
     * @param requests requests
     * @return order mass
     */
    @Override
    public int countOrderMass(List<Request> requests) {//todo возможно логичнее перенести этот метод в requestService
        int mass = 0;
        for (Request r :
                requests) {
            mass += r.getGoodForRequest().getMass();
        }
        logger.info("Mass of order: " + mass);
        return mass;
    }

    /**
     * Connects van with order.
     *
     * @param van
     * @param order
     */
    @Override
    @Transactional
    public void setVanToOrder(Van van, Order order) {
        order.setVan(van);
        update(order);
    }

    /**
     * Connects drivers with order.
     *
     * @param selectedDrivers
     * @param order
     */
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

    /**
     * Deletes link between order and van.
     *
     * @param order
     * @param van
     */
    @Override
    @Transactional
    public void breakLinkWithVan(Order order, Van van) {
        order.setVan(null);
        update(order);
        logger.info("Van " + van + " is deleted from order " + order.getNumber());
    }
}
