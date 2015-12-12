package service.API;

import entities.Order;
import java.util.ArrayList;

public interface OrderService extends GenericService<Order,Integer>{

    ArrayList<Order> getOrdersDone();

    ArrayList<Order> getOrdersProcess();

    Order getByNumber(Integer number);


}
