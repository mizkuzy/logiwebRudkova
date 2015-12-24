package ru.tsystems.logiweb;

import ru.tsystems.logiweb.entities.*;
import ru.tsystems.logiweb.entities.statusesAndStates.*;
import ru.tsystems.logiweb.service.API.*;
import ru.tsystems.logiweb.service.IMPL.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * здесь мы заполняем БД базовыми значениями
 */
public class RunToDB {

    public static final String PERSISTENCE_UNIT = "MYLogiweb";

    public static EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);

    public static EntityManager em = emf.createEntityManager();


    public static void main(String[] args) {


        em.getTransaction().begin();


        Employee employee1 = new Employee("dr1@lw.ru", "pswd", POSITION.DRIVER);
        Employee employee2 = new Employee("dr2@lw.ru", "pswd", POSITION.DRIVER);
        Employee employee3 = new Employee("man1@lw.ru", "pswd", POSITION.OTHER);
        Employee employee4 = new Employee("man2@lw.ru", "pswd", POSITION.OTHER);

        Driver driver1 = new Driver("Petr", "Severe");
        Driver driver2 = new Driver("Andrey", "Knyaz");

        employee1.setDriverFK(driver1);
        employee2.setDriverFK(driver2);

        em.persist(driver1);
        em.persist(driver2);

        em.persist(employee1);
        em.persist(employee2);
        em.persist(employee3);
        em.persist(employee4);

        Rout rout1 = new Rout("Saint-Petersburg", "Veliky Novgorod", 194, 3);
        Rout rout2 = new Rout("Saint-Petersburg", "Pskov", 408, 5);
        Rout rout3 = new Rout("Saint-Petersburg", "Kaliningrad", 1076, 14);
        Rout rout4 = new Rout("Veliky Novgorod", "Pskov", 214, 3);
        Rout rout5 = new Rout("Veliky Novgorod", "Kaliningrad", 882, 11);
        Rout rout6 = new Rout("Pskov", "Kaliningrad", 668, 8);
        Rout rout7 = new Rout("Saint-Petersburg", "Petrozavodsk", 428, 5);
        Rout rout8 = new Rout("Saint-Petersburg", "Murmansk", 1362, 17);
        Rout rout9 = new Rout("Petrozavodsk", "Murmansk", 934, 12);
        Rout rout10 = new Rout("Saint-Petersburg", "Petrozavodsk", 428, 5);
        Rout rout11 = new Rout("Saint-Petersburg", "Arhangelsk", 1413, 18);
        Rout rout12 = new Rout("Saint-Petersburg", "Nary'an-Mar", 3536, 45);
        Rout rout13 = new Rout("Petrozavodsk", "Arhangelsk", 985, 12);
        Rout rout14 = new Rout("Petrozavodsk", "Nary'an-Mar", 3108, 39);
        Rout rout15 = new Rout("Arhangelsk", "Nary'an-Mar", 2123, 27);
        Rout rout16 = new Rout("Saint-Petersburg", "Vologda", 654, 8);
        Rout rout17 = new Rout("Saint-Petersburg", "Siktivkar", 1498, 19);
        Rout rout18 = new Rout("Vologda", "Siktivkar", 844, 11);

        RouteLabel routeLabel1 = new RouteLabel("yellow");
        RouteLabel routeLabel2 = new RouteLabel("green");
        RouteLabel routeLabel3 = new RouteLabel("purple");
        RouteLabel routeLabel4 = new RouteLabel("blue");

        rout1.setRouteLabelFK(routeLabel1);
        rout2.setRouteLabelFK(routeLabel1);
        rout3.setRouteLabelFK(routeLabel1);
        rout4.setRouteLabelFK(routeLabel1);
        rout5.setRouteLabelFK(routeLabel1);
        rout6.setRouteLabelFK(routeLabel1);
        rout7.setRouteLabelFK(routeLabel2);
        rout8.setRouteLabelFK(routeLabel2);
        rout9.setRouteLabelFK(routeLabel2);
        rout10.setRouteLabelFK(routeLabel3);
        rout11.setRouteLabelFK(routeLabel3);
        rout12.setRouteLabelFK(routeLabel3);
        rout13.setRouteLabelFK(routeLabel3);
        rout14.setRouteLabelFK(routeLabel3);
        rout15.setRouteLabelFK(routeLabel3);
        rout16.setRouteLabelFK(routeLabel4);
        rout17.setRouteLabelFK(routeLabel4);
        rout18.setRouteLabelFK(routeLabel4);

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

        Van van1 = new Van("AA12345", 4, 10);
        Van van2 = new Van("BB12345", 3, 20);
        Van van3 = new Van("CC12345", 3, 30);
        Van van4 = new Van("EE12345", 2, 40);
        Van van5 = new Van("HH12345", 4, 10);
        Van van6 = new Van("KK12345", 3, 20);
        Van van7 = new Van("MM12345", 3, 30);
        Van van8 = new Van("OO12345", 2, 40);
        Van van9 = new Van("PP12345", 2, 50);
        Van van10 = new Van("TT12345", 2, 10);

        van1.setRoutLabelForVan(routeLabel1);
        van2.setRoutLabelForVan(routeLabel1);
        van3.setRoutLabelForVan(routeLabel1);
        van4.setRoutLabelForVan(routeLabel2);
        van5.setRoutLabelForVan(routeLabel2);
        van6.setRoutLabelForVan(routeLabel3);
        van7.setRoutLabelForVan(routeLabel3);
        van8.setRoutLabelForVan(routeLabel4);
        van9.setRoutLabelForVan(routeLabel4);
        van10.setRoutLabelForVan(routeLabel4);

        em.persist(van1);
        em.persist(van2);
        em.persist(van3);
        em.persist(van4);
        em.persist(van5);
        em.persist(van6);
        em.persist(van7);
        em.persist(van8);
        em.persist(van9);
        em.persist(van10);

        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
