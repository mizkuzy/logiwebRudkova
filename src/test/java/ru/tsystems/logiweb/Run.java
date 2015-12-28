package ru.tsystems.logiweb;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.tsystems.logiweb.entities.Driver;
import ru.tsystems.logiweb.entities.Employee;
import ru.tsystems.logiweb.entities.statusesAndStates.POSITION;
import ru.tsystems.logiweb.service.API.DriverService;
import ru.tsystems.logiweb.service.API.EmployeeService;
import ru.tsystems.logiweb.service.IMPL.DriverServiceImpl;
import ru.tsystems.logiweb.service.IMPL.EmployeeServiceImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Run {

    /*public static final String PERSISTENCE_UNIT = "MYLogiweb";

    public static EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);

    public static EntityManager em = emf.createEntityManager();

    private static Logger logger = Logger.getLogger(Run.class);*/

    public static void main(String[] args) {
    }
}
