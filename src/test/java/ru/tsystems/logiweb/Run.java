package ru.tsystems.logiweb;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.tsystems.logiweb.entities.Driver;
import ru.tsystems.logiweb.entities.Employee;
import ru.tsystems.logiweb.entities.Rout;
import ru.tsystems.logiweb.entities.RouteLabel;
import ru.tsystems.logiweb.entities.statusesAndStates.POSITION;
import ru.tsystems.logiweb.service.API.DriverService;
import ru.tsystems.logiweb.service.API.EmployeeService;
import ru.tsystems.logiweb.service.IMPL.DriverServiceImpl;
import ru.tsystems.logiweb.service.IMPL.EmployeeServiceImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Run {

    public static final String PERSISTENCE_UNIT = "MYLogiweb";

    public static EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);

    public static EntityManager em = emf.createEntityManager();

    private static Logger logger = Logger.getLogger(Run.class);

    public static void main(String[] args) {
        Rout rout1 = new Rout("Saint-Petersburg", "Veliky_Novgorod", 194, 3);
        Rout rout19 = new Rout("Veliky_Novgorod", "Saint-Petersburg", 194, 3);
        Rout rout2 = new Rout("Saint-Petersburg", "Pskov", 408, 5);
        Rout rout20 = new Rout("Pskov", "Saint-Petersburg", 408, 5);
        Rout rout3 = new Rout("Saint-Petersburg", "Kaliningrad", 1076, 14);
        Rout rout21 = new Rout("Kaliningrad", "Saint-Petersburg", 1076, 14);
        Rout rout4 = new Rout("Veliky_Novgorod", "Pskov", 214, 3);
        Rout rout22 = new Rout("Pskov", "Veliky_Novgorod", 214, 3);
        Rout rout5 = new Rout("Veliky_Novgorod", "Kaliningrad", 882, 11);
        Rout rout23 = new Rout("Kaliningrad", "Veliky_Novgorod", 882, 11);
        Rout rout6 = new Rout("Pskov", "Kaliningrad", 668, 8);
        Rout rout24 = new Rout("Kaliningrad", "Pskov", 668, 8);
        Rout rout7 = new Rout("Saint-Petersburg", "Petrozavodsk", 428, 5);
        Rout rout25 = new Rout("Petrozavodsk", "Saint-Petersburg", 428, 5);
        Rout rout8 = new Rout("Saint-Petersburg", "Murmansk", 1362, 17);
        Rout rout26 = new Rout("Murmansk", "Saint-Petersburg", 1362, 17);
        Rout rout9 = new Rout("Petrozavodsk", "Murmansk", 934, 12);
        Rout rout27 = new Rout("Murmansk", "Petrozavodsk", 934, 12);
        Rout rout10 = new Rout("Saint-Petersburg", "Cherepovec", 538, 6);
        Rout rout28 = new Rout("Cherepovec", "Saint-Petersburg", 538, 6);
        Rout rout11 = new Rout("Saint-Petersburg", "Arhangelsk", 1413, 18);
        Rout rout29 = new Rout("Arhangelsk", "Saint-Petersburg", 1413, 18);
        Rout rout12 = new Rout("Saint-Petersburg", "Naryan-Mar", 3536, 45);
        Rout rout30 = new Rout("Naryan-Mar", "Saint-Petersburg", 3536, 45);
        Rout rout13 = new Rout("Cherepovec", "Arhangelsk", 899, 9);
        Rout rout31 = new Rout("Arhangelsk", "Cherepovec", 899, 9);
        Rout rout14 = new Rout("Cherepovec", "Naryan-Mar", 2169, 34);
        Rout rout32 = new Rout("Naryan-Mar", "Cherepovec", 2169, 34);
        Rout rout15 = new Rout("Arhangelsk", "Naryan-Mar", 2123, 27);
        Rout rout33 = new Rout("Naryan-Mar", "Arhangelsk", 2123, 27);
        Rout rout16 = new Rout("Saint-Petersburg", "Vologda", 654, 8);
        Rout rout34 = new Rout("Vologda", "Saint-Petersburg", 654, 8);
        Rout rout17 = new Rout("Saint-Petersburg", "Siktivkar", 1498, 19);
        Rout rout35 = new Rout("Siktivkar", "Saint-Petersburg", 1498, 19);
        Rout rout18 = new Rout("Vologda", "Siktivkar", 844, 11);
        Rout rout36 = new Rout("Siktivkar", "Vologda", 844, 11);

        RouteLabel routeLabel1 = new RouteLabel("yellow");
        RouteLabel routeLabel2 = new RouteLabel("green");
        RouteLabel routeLabel3 = new RouteLabel("purple");
        RouteLabel routeLabel4 = new RouteLabel("blue");

        rout1.setRouteLabelFK(routeLabel1);
        rout19.setRouteLabelFK(routeLabel1);
        rout2.setRouteLabelFK(routeLabel1);
        rout20.setRouteLabelFK(routeLabel1);
        rout3.setRouteLabelFK(routeLabel1);
        rout21.setRouteLabelFK(routeLabel1);
        rout4.setRouteLabelFK(routeLabel1);
        rout22.setRouteLabelFK(routeLabel1);
        rout5.setRouteLabelFK(routeLabel1);
        rout23.setRouteLabelFK(routeLabel1);
        rout6.setRouteLabelFK(routeLabel1);
        rout24.setRouteLabelFK(routeLabel1);
        rout7.setRouteLabelFK(routeLabel2);
        rout25.setRouteLabelFK(routeLabel2);
        rout8.setRouteLabelFK(routeLabel2);
        rout26.setRouteLabelFK(routeLabel2);
        rout9.setRouteLabelFK(routeLabel2);
        rout27.setRouteLabelFK(routeLabel2);
        rout10.setRouteLabelFK(routeLabel3);
        rout28.setRouteLabelFK(routeLabel3);
        rout11.setRouteLabelFK(routeLabel3);
        rout29.setRouteLabelFK(routeLabel3);
        rout12.setRouteLabelFK(routeLabel3);
        rout30.setRouteLabelFK(routeLabel3);
        rout13.setRouteLabelFK(routeLabel3);
        rout31.setRouteLabelFK(routeLabel3);
        rout14.setRouteLabelFK(routeLabel3);
        rout32.setRouteLabelFK(routeLabel3);
        rout15.setRouteLabelFK(routeLabel3);
        rout33.setRouteLabelFK(routeLabel3);
        rout16.setRouteLabelFK(routeLabel4);
        rout34.setRouteLabelFK(routeLabel4);
        rout17.setRouteLabelFK(routeLabel4);
        rout35.setRouteLabelFK(routeLabel4);
        rout18.setRouteLabelFK(routeLabel4);
        rout36.setRouteLabelFK(routeLabel4);

        em.persist(routeLabel1);
        em.persist(routeLabel2);
        em.persist(routeLabel3);
        em.persist(routeLabel4);

        em.persist(rout1);
        em.persist(rout2);
        em.persist(rout3);
        em.persist(rout4);
        em.persist(rout5);
        em.persist(rout6);
        em.persist(rout7);
        em.persist(rout8);
        em.persist(rout9);
        em.persist(rout10);
        em.persist(rout11);
        em.persist(rout12);
        em.persist(rout13);
        em.persist(rout14);
        em.persist(rout15);
        em.persist(rout16);
        em.persist(rout17);
        em.persist(rout18);
        em.persist(rout19);
        em.persist(rout20);
        em.persist(rout21);
        em.persist(rout22);
        em.persist(rout23);
        em.persist(rout24);
        em.persist(rout25);
        em.persist(rout26);
        em.persist(rout27);
        em.persist(rout28);
        em.persist(rout29);
        em.persist(rout30);
        em.persist(rout31);
        em.persist(rout32);
        em.persist(rout33);
        em.persist(rout34);
        em.persist(rout35);
        em.persist(rout36);

        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
