package ru.tsystems.logiweb.servicesJUnit;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.logiweb.entities.Driver;
import org.junit.Test;
import ru.tsystems.logiweb.entities.Order;
import ru.tsystems.logiweb.entities.statusesAndStates.DriverState;
import ru.tsystems.logiweb.entities.statusesAndStates.DriverStatus;
import ru.tsystems.logiweb.service.API.DriverService;
import ru.tsystems.logiweb.service.API.OrderService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@Transactional
@SuppressWarnings("deprecation")
@ContextConfiguration(locations = "/spring.xml")
public class DriverServiceTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private DriverService driverService;
    @Autowired
    private OrderService orderService;

    /**
     * Tests creating driver.
     */
    @Test
    public void testCreateDriver() {

        int sizeAllDriversBeforeTest = driverService.getAll().size();

        Driver driver = new Driver("Test", "Testov");
        driverService.create(driver);
        int sizeAllDriversAfterTest = driverService.getAll().size();
        assertTrue(sizeAllDriversAfterTest > sizeAllDriversBeforeTest);

        driverService.delete(driver);
    }

    /**
     * Tests reading driver.
     */
    @Test
    public void testReadDriver() {
        Driver driver = new Driver("Test", "Testov");
        driverService.create(driver);
        int driverID = driver.getId();

        Driver driverTest = driverService.read(driverID);
        assertNotNull(driverTest);

        driverService.delete(driver);
    }

    /**
     * Tests reading driver.
     */
    @Test
    public void testUpdateDriver() {
        //preparing
        Driver driver = new Driver("Test", null);
        driverService.create(driver);

        //test
        driver.setSurname("Testov");
        driverService.update(driver);
        assertNotNull(driver.getSurname());

        //cleaning DB
        driverService.delete(driver);
    }


    /**
     * Tests deleting driver.
     */
    @Test
    public void testDeleteDriver() {
        Driver driver = new Driver("Test", "Testov");
        driverService.create(driver);
        int sizeAllDriversBeforeTest = driverService.getAll().size();

        driverService.delete(driver);
        int sizeAllDriversAfterTest = driverService.getAll().size();
        assertTrue(sizeAllDriversAfterTest < sizeAllDriversBeforeTest);
    }

    /**
     * Tests getting all drivers.
     */
    @Test
    public void testGetAllDrivers() {
        List<Driver> drivers = driverService.getAll();
        assertNotNull(drivers);
    }

    /**
     * Tests that drivers' statuses were changed to required.
     */
    @Test
    public void testChangingDriverStatus() {
        //preparing
        Driver driver1 = new Driver("Driver1", "Testov");
        driverService.create(driver1);

        //test
        List<Driver> selectedDrivers = new ArrayList<>(2);
        selectedDrivers.add(driver1);

        driverService.changeDriversStatuses(selectedDrivers, DriverStatus.BUSY);
        assertEquals(DriverStatus.BUSY, driver1.getStatusDriver());

        //cleaning DB
        driverService.delete(driver1);
    }

    /**
     * Tests that drivers' states were changed to required
     */
    @Test
    public void testChangingDriverState() {
        //preparing
        Driver driver1 = new Driver("Driver1", "Testov");
        driverService.create(driver1);

        //test
        List<Driver> selectedDrivers = new ArrayList<>(2);
        selectedDrivers.add(driver1);

        driverService.changeDriversStates(selectedDrivers, DriverState.DRIVE);
        assertEquals(DriverState.DRIVE, driver1.getState());

        //cleaning DB
        driverService.delete(driver1);
    }

    /**
     * Tests getting list of drivers that connected to required order.
     */
    @Test
    public void testGetBusyDrivers() {
        //prepare
        Order order = new Order();
        orderService.create(order);
        Driver driver = new Driver("Test", "Testov");
        driver.setCurrentOrder(order);
        driverService.create(driver);

        //test
        List<Driver> drivers;
        drivers = driverService.getBusyDrivers(order.getIdOrder());
        int sizeDriversListAfterTest = drivers.size();
        assertTrue(sizeDriversListAfterTest == 1);

        //cleaning DB
        driverService.delete(driver);
        orderService.delete(order);
    }

    /**
     * Tests that link between required order and list of drivers was broken.
     */
    @Test
    public void testBreakLinksBetweenDriverAndOrder() {
        //prepare
        Order order = new Order();
        orderService.create(order);
        Driver driver = new Driver("Test", "Testov");
        driver.setCurrentOrder(order);
        driverService.create(driver);

        //test
        List<Driver> drivers = new ArrayList<>();
        drivers.add(driver);
        driverService.breakLinks(drivers, order);
        assertNull(driver.getCurrentOrder());

        //cleaning DB
        driverService.delete(driver);
        orderService.delete(order);
    }

    /**
     * Tests that given work hours were set to given list of Drivers.
     */
    @Test
    public void testSetWorkHoursToDriver() {
        //prepare
        Driver driver = new Driver("Test", "Testov");
        driverService.create(driver);
        List<Driver> drivers = new ArrayList<>();
        drivers.add(driver);

        //test
        Integer workHours = 10;
        driverService.setWorkHours(drivers, workHours);
        assertEquals(workHours, driver.getWorkHours());

        //cleaning DB
        driverService.delete(driver);
    }

    /**
     * Tests that work hours of all drivers were set to zero.
     */
    @Test
    public void testSetWorksHoursToZeroAllDrivers() {
        //prepare
        List<Driver> allDrivers = driverService.getAll();
        List<Integer> currentWorkHours = new ArrayList<>(allDrivers.size());
        if (allDrivers != null) {
            for (Driver driver : allDrivers) {
                currentWorkHours.add(driver.getWorkHours());
            }
        }

        //test
        Integer zero = 0;

        driverService.setWorksHoursToZeroAllDrivers();
        for (Driver driver: allDrivers){
            assertEquals(driver.getWorkHours(), zero);
        }

        //cleaning DB. Setting back work hours before test.
        for (int i = 0; i < allDrivers.size(); i++) {
            allDrivers.get(i).setWorkHours(currentWorkHours.get(i));
            driverService.update( allDrivers.get(i));
        }

    }

}