package service.IMPL;

import dao.API.OrderGenericDAO;
import dao.IMPL.OrderGenericDAOImpl;
import entities.Order;
import entities.statusesAndStates.OrderStatus;
import service.API.OrderService;

import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private OrderGenericDAO orderDAO = new OrderGenericDAOImpl();

    @Override
    public void create(Order entity) {
        orderDAO.create(entity);
    }

    @Override
    public Order read(Integer id) {
        return orderDAO.read(id);
    }

    @Override
    public void update(Order entity) {
        orderDAO.update(entity);
    }

    @Override
    public void delete(Order entity) {
        orderDAO.delete(entity);
    }

    @Override
    public List<Order> getAll() {
        return orderDAO.getAll();
    }

    //TODO TEST

    /**
     * Get List of orders with OrderStatus=DONE.
     *
     * @return
     */
    //TODO кидает NullEx, если нет заказов с таким статусом
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

    //TODO TEST

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

    @Override
    public Order getByNumber(Integer number) {
        return orderDAO.getByNumber(number);
    }
}
