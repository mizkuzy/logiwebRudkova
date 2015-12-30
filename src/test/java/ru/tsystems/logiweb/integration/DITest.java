package ru.tsystems.logiweb.integration;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import ru.tsystems.logiweb.dao.API.*;
import ru.tsystems.logiweb.service.API.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Tests of entity manager injection.
 */
@ContextConfiguration(locations = "classpath*:spring.xml")
public class DITest extends AbstractJUnit4SpringContextTests{

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private DriverService driverService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private GoodService goodService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private RequestService requestService;
    @Autowired
    private RoutLabelService routLabelService;
    @Autowired
    private RoutService routService;
    @Autowired
    private VanService vanService;

    @Autowired
    private DriverGenericDAO driverGenericDAO;
    @Autowired
    private EmployeeGenericDAO employeeGenericDAO;
    @Autowired
    private GoodGenericDAO goodGenericDAO;
    @Autowired
    private OrderGenericDAO orderGenericDAO;
    @Autowired
    private RequestGenericDAO requestGenericDAO;
    @Autowired
    private RoutGenericDAO routGenericDAO;
    @Autowired
    private RoutLabelGenericDAO routLabelGenericDAO;
    @Autowired
    private VanGenericDAO vanGenericDAO;

    @Test
    public void testEntityManager() {
        assertNotNull(entityManager);
    }

    @Test
    public void testDriverService() {
        assertNotNull(driverService);
    }

    @Test
    public void testEmployeeService() {
        assertNotNull(employeeService);
    }

    @Test
    public void testGoodService() {
        assertNotNull(goodService);
    }

    @Test
    public void testOrderService() {
        assertNotNull(orderService);
    }

    @Test
    public void testRequestService() {
        assertNotNull(requestService);
    }

    @Test
    public void testRoutLabelService(){
        assertNotNull(routLabelService);
    }

    @Test
    public void testRoutService(){
        assertNotNull(routService);
    }

    @Test
    public void testVanService(){
        assertNotNull(vanService);
    }

    @Test
    public void testDriverDAO(){
        assertNotNull(driverGenericDAO);
    }

    @Test
    public void testEmployeeDAO(){
        assertNotNull(employeeGenericDAO);
    }

    @Test
    public void testGoodDAO(){
        assertNotNull(goodGenericDAO);
    }

    @Test
    public void testOrderDAO(){
        assertNotNull(orderGenericDAO);
    }

    @Test
    public void testRequestDAO(){
        assertNotNull(requestGenericDAO);
    }

    @Test
    public void testRoutDAO(){
        assertNotNull(routGenericDAO);
    }

    @Test
    public void testRoutLabelDAO(){
        assertNotNull(routLabelGenericDAO);
    }

    @Test
    public void testVanDAO(){
        assertNotNull(vanGenericDAO);
    }

    //TODO полчему не работает?
    @Test
    public void testCheckEmailAndPassword(){
        boolean isVerified = employeeService.checkEmailAndPassword("driver1@logiweb.ru", "pswd");
        assertTrue(isVerified);
    }

}
