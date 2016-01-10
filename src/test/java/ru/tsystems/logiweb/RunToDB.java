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
        Employee employee5 = new Employee("dr5@lw.ru", "pswd", POSITION.DRIVER);
        Employee employee6 = new Employee("dr6@lw.ru", "pswd", POSITION.DRIVER);
        Employee employee7 = new Employee("dr7@lw.ru", "pswd", POSITION.DRIVER);
        Employee employee8 = new Employee("dr8@lw.ru", "pswd", POSITION.DRIVER);
        Employee employee9 = new Employee("dr9@lw.ru", "pswd", POSITION.DRIVER);
        Employee employee10 = new Employee("dr10@lw.ru", "pswd", POSITION.DRIVER);
        Employee employee11 = new Employee("dr11@lw.ru", "pswd", POSITION.DRIVER);
        Employee employee12 = new Employee("dr12@lw.ru", "pswd", POSITION.DRIVER);
        Employee employee13 = new Employee("dr13@lw.ru", "pswd", POSITION.DRIVER);
        Employee employee14 = new Employee("dr14@lw.ru", "pswd", POSITION.DRIVER);

        Driver driver1 = new Driver("Petr", "Severe");
        Driver driver2 = new Driver("Andrey", "Knyaz");
        Driver driver3 = new Driver("Nikolay", "Fomin");
        Driver driver4 = new Driver("Gerakl", "Zevsov");
        Driver driver5 = new Driver("Mihail", "Gogol");
        Driver driver6 = new Driver("Ivan", "Vanin");
        Driver driver7 = new Driver("Zurab", "Ivanov");
        Driver driver8 = new Driver("Sergey", "Jukov");
        Driver driver9 = new Driver("Gustav", "Bok");
        Driver driver10 = new Driver("Till", "Lindemann");
        Driver driver11 = new Driver("Henry", "Ford");
        Driver driver12 = new Driver("Max", "Bobkin");

        employee1.setDriverFK(driver1);
        employee2.setDriverFK(driver2);
        employee5.setDriverFK(driver3);
        employee6.setDriverFK(driver4);
        employee7.setDriverFK(driver5);
        employee8.setDriverFK(driver6);
        employee9.setDriverFK(driver7);
        employee10.setDriverFK(driver8);
        employee11.setDriverFK(driver9);
        employee12.setDriverFK(driver10);
        employee13.setDriverFK(driver11);
        employee14.setDriverFK(driver12);

        em.persist(driver1);
        em.persist(driver2);
        em.persist(driver3);
        em.persist(driver4);
        em.persist(driver5);
        em.persist(driver6);
        em.persist(driver7);
        em.persist(driver8);
        em.persist(driver9);
        em.persist(driver10);
        em.persist(driver11);
        em.persist(driver12);

        employee1.setPersonalNumber(driver1.getId());
        employee2.setPersonalNumber(driver2.getId());
        employee5.setPersonalNumber(driver3.getId());
        employee6.setPersonalNumber(driver4.getId());
        employee7.setPersonalNumber(driver5.getId());
        employee8.setPersonalNumber(driver6.getId());
        employee9.setPersonalNumber(driver7.getId());
        employee10.setPersonalNumber(driver8.getId());
        employee11.setPersonalNumber(driver9.getId());
        employee12.setPersonalNumber(driver10.getId());
        employee13.setPersonalNumber(driver11.getId());
        employee14.setPersonalNumber(driver12.getId());

        em.persist(employee1);
        em.persist(employee2);
        em.persist(employee3);
        em.persist(employee4);
        em.persist(employee5);
        em.persist(employee6);
        em.persist(employee7);
        em.persist(employee8);
        em.persist(employee9);
        em.persist(employee10);
        em.persist(employee11);
        em.persist(employee12);
        em.persist(employee13);
        em.persist(employee14);

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

        Van van1 = new Van("AA12345", 2, 10);
        Van van2 = new Van("BB12345", 2, 20);
        Van van3 = new Van("CC12345", 2, 30);
        Van van4 = new Van("EE12345", 3, 40);
        Van van5 = new Van("HH12345", 3, 10);
        Van van6 = new Van("KK12345", 4, 20);
        Van van7 = new Van("MM12345", 4, 30);
        Van van8 = new Van("OO12345", 3, 40);
        Van van9 = new Van("PP12345", 3, 50);
        Van van10 = new Van("TT12345", 3, 10);
        Van van11 = new Van("AB54321", 4, 7);
        Van van12 = new Van("CC53261", 3, 12);


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
        van11.setRoutLabelForVan(routeLabel3);
        van12.setRoutLabelForVan(routeLabel2);

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
        em.persist(van11);
        em.persist(van12);

        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
