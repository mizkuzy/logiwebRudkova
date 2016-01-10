package ru.tsystems.logiweb.servicesJUnit;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.logiweb.entities.Good;
import ru.tsystems.logiweb.entities.Order;
import ru.tsystems.logiweb.entities.Request;
import ru.tsystems.logiweb.entities.Van;
import ru.tsystems.logiweb.exceptions.CustomLogiwebException;
import ru.tsystems.logiweb.service.API.GoodService;
import ru.tsystems.logiweb.service.API.OrderService;
import ru.tsystems.logiweb.service.API.RequestService;
import ru.tsystems.logiweb.service.API.VanService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@Transactional
@SuppressWarnings("deprecation")
@ContextConfiguration(locations = "/spring.xml")
public class OrderServiceTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private OrderService orderService;
    @Autowired
    private GoodService goodService;
    @Autowired
    private RequestService requestService;
    @Autowired
    private VanService vanService;

    /**
     * Tests creating order.
     */
    @Test
    public void testCreateOrder() {
        int sizeAllOrdersBeforeTest = orderService.getAll().size();

        Order order = new Order();
        orderService.create(order);
        int sizeAllOrdersAfterTest = orderService.getAll().size();
        assertTrue(sizeAllOrdersBeforeTest < sizeAllOrdersAfterTest);
    }

    /**
     * Tests reading order.
     */
    @Test
    public void testReadOrder() {
        //preparing
        Order order = new Order();
        orderService.create(order);
        int orderID = order.getIdOrder();

        //test
        Order orderTest = orderService.read(orderID);
        assertNotNull(orderTest);

        //cleaning DB
        orderService.delete(order);
    }

    /**
     * Tests updating order.
     */
    @Test
    public void testUpdateOrder() {
        //preparing
        Order order = new Order();
        orderService.create(order);

        //test
        order.setNumber(100);
        orderService.update(order);
        assertNotNull(order.getNumber());

        //cleaning DB
        orderService.delete(order);
    }

    /**
     * Tests deleting order.
     */
    @Test
    public void testDeleteOrder() {
        //preparing
        Order order = new Order();
        orderService.create(order);

        //test
        int sizeAllOrdersBeforeTest = orderService.getAll().size();
        orderService.delete(order);
        int sizeAllOrdersAfterTest = orderService.getAll().size();
        assertTrue(sizeAllOrdersBeforeTest > sizeAllOrdersAfterTest);
    }

    /**
     * Gets entity with corresponding number.
     */
    @Test
    public void testGetByNumberSuccess() {
        //preparing
        Order order = new Order();
        order.setNumber(100);
        orderService.create(order);
        Integer orderNumber = order.getNumber();

        //test
        Order orderTest = orderService.getByNumber(orderNumber);
        assertNotNull(orderTest);

        //cleaning DB
        orderService.delete(order);
    }

    /**
     * Does not get entity with corresponding number, because this number is wrong.
     * Throws CustomLogiwebException.
     */
    @Test(expected = CustomLogiwebException.class)
    public void testGetByNumberFail() {

        Integer wrongOrderNumber = 100000;
        Order orderTest = orderService.getByNumber(wrongOrderNumber);
    }

    /**
     * Tests that new order with requests is created.
     */
    @Test
    public void testAddNewOrder() {
        //preparing
        List<Request> requests = new ArrayList<>(1);
        Request request = new Request();
        requestService.create(request);
        requests.add(request);

        //test
        int sizeAllOrdersBeforeTest = orderService.getAll().size();
        orderService.addNewOrder(requests);
        int sizeAllOrdersAfterTest = orderService.getAll().size();

        //Deleting new order is impossible in this method or I just don't know how to do it.
        // So I deleted manually this  entity after this test.
        requestService.delete(request);
    }

    /**
     * Tests counting of goods' mass.
     */
    @Test
    public void testCountOrderMass() {
        //preparing
        Good good1 = new Good("chocolate", 10);
        Good good2 = new Good("books", 15);
        goodService.create(good1);
        goodService.create(good2);
        int goodsMass = good1.getMass() + good2.getMass();

        List<Request> requests = new ArrayList<>(2);
        Request request1 = new Request();
        Request request2 = new Request();
        request1.setGoodForRequest(good1);
        request2.setGoodForRequest(good2);
        requestService.create(request1);
        requestService.create(request2);
        requests.add(request1);
        requests.add(request2);

        //test
        int orderMass = orderService.countOrderMass(requests);
        System.out.println(orderMass == goodsMass);
        assertEquals(goodsMass, orderMass);

        //cleaning DB
        requestService.delete(request1);
        requestService.delete(request2);
        goodService.delete(good1);
        goodService.delete(good2);
    }

    /**
     * Sets given van to required order.
     */
    @Test
    public void setVanToOrder() {

        Van van = new Van("AA12345",2,10);
        vanService.create(van);
        Order order = new Order();
        orderService.setVanToOrder(van, order);
        assertNotNull(order.getVan());

        //cleaning DB
       //smth wrong with cleaning. I delete entities manually
    }
}
