package ru.tsystems.logiweb;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.tsystems.logiweb.service.API.DriverService;
import ru.tsystems.logiweb.service.API.EmployeeService;
import ru.tsystems.logiweb.service.IMPL.DriverServiceImpl;
import ru.tsystems.logiweb.service.IMPL.EmployeeServiceImpl;

public class Run {

    /*public static final String PERSISTENCE_UNIT = "MYLogiweb";

    public static EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);

    public static EntityManager em = emf.createEntityManager();*/

    private static Logger logger = Logger.getLogger(Run.class);

    public static void main(String[] args) {

        /*ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

        EmployeeService employeeService = (EmployeeServiceImpl) context.getBean("employeeService");

        Boolean validationIsOK = employeeService.checkEmailAndPassword("driver1@logiweb.ru", "pswd");
        System.out.println(validationIsOK);*/

        /*logger.debug("inside main()");
        logger.info("Hello logger");
        logger.error("Error!", new Exception("Exception"));*/


        /*Employee employee1 = new Employee("driver1@logiweb.ru","pswd", POSITION.DRIVER);
        Employee employee2=new Employee("driver2@logiweb.ru","pswd", POSITION.DRIVER);

        Driver driver1 = new Driver("ivan","ivanov");
        Driver driver2 = new Driver("lancelot","rudkov");

        employee1.setDriverFK(driver1);
        employee2.setDriverFK(driver2);

        em.persist(driver1);
        em.persist(driver2);

        em.persist(employee1);
        em.persist(employee2);

        Rout rout1 = new Rout("Saint-Petersburg", "Veliky Novgorod", 194, 3);
        RouteLabel routeLabel1 = new RouteLabel("yellow");
        rout1.setRouteLabelFK(routeLabel1);
        em.persist(routeLabel1);
        em.persist(rout1);

        Van van1 = new Van("AA12345", 4, 10);
        van1.setRoutLabelForVan(routeLabel1);
        em.persist(van1);

        Request request1 = new Request();
        Request request2 = new Request();

        request1.setStatusRequest(RequestStatus.NO);
        request2.setStatusRequest(RequestStatus.NO);

        request1.setRoutForRequest(rout1);
        request2.setRoutForRequest(rout1);

        Good good1 = new Good("products", 20);
        Good good2 = new Good("books", 30);

        request1.setGoodForRequest(good1);
        request2.setGoodForRequest(good2);

        em.persist(good1);
        em.persist(good2);

        em.persist(request1);
        em.persist(request2);

        Order order = new Order();

        order.setVan(van1);

        driver1.setCurrentOrder(order);
        driver2.setCurrentOrder(order);

        order.addDriver(driver1);
        order.addDriver(driver2);

        request1.setCurrentOrder(order);
        request2.setCurrentOrder(order);

        em.persist(order);

        List<Driver> drivers = order.getDrivers();

        for (Driver d : drivers) {
            System.out.println(d.getName());
        }*/

        /*em.getTransaction().begin();
        em.getTransaction().commit();
        em.close();
        emf.close();*/
    }
}
