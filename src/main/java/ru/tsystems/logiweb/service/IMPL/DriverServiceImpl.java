package ru.tsystems.logiweb.service.IMPL;

import org.apache.log4j.Logger;
import ru.tsystems.logiweb.dao.API.DriverGenericDAO;
import ru.tsystems.logiweb.entities.Driver;
import ru.tsystems.logiweb.entities.Order;
import ru.tsystems.logiweb.entities.statusesAndStates.DriverState;
import ru.tsystems.logiweb.entities.statusesAndStates.DriverStatus;
import ru.tsystems.logiweb.service.API.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * An implementation of DriverService API.
 */
@Service("driverService")
public class DriverServiceImpl implements DriverService {

    public static final int MAX_WORK_HOURS_PER_MONTH = 176;

    private Logger logger = Logger.getLogger(DriverServiceImpl.class);

    //TODO ещё лучше разобраться с этой аннотацией
    @Autowired
    private DriverGenericDAO driverDao;

    /**
     * Create required entity.
     *
     * @param entity
     */
    @Override
    @Transactional
    public void create(Driver entity) {
        driverDao.create(entity);
    }

    /**
     * Read required entity.
     *
     * @param id
     * @return entity
     */
    @Override
    @Transactional
    public Driver read(Integer id) {
        return driverDao.read(id);
    }

    /**
     * Update required entity.
     *
     * @param entity
     */
    @Override
    @Transactional
    public void update(Driver entity) {
        driverDao.update(entity);
    }

    /**
     * Delete required entity.
     *
     * @param entity
     */
    @Override
    @Transactional
    public void delete(Driver entity) {
        driverDao.delete(entity);
    }

    /**
     * Get list of required ru.tsystems.logiweb.entities.
     *
     * @return list of ru.tsystems.logiweb.entities
     */
    @Override
    @Transactional
    public List getAll() {
        return driverDao.getAll();
    }

    /**
     * Choose appropriate drivers from all drivers who corresponds to conditions:
     * has not enough work hours per month,  DriverState=WORK, DriverStatus=FREE.
     *
     * @param orderHours
     * @return list of appropriate drivers for this order
     */
    @Override
    @Transactional
    public List getAppropriateDrivers(int orderHours) {
        checkFirstMonthDay();
        //todo смену месяцев надо учесть
        List<Driver> allDrivers = getAll();
        List<Driver> appropriateDrivers = new ArrayList<>(allDrivers.size());//size of this list obviously will not more then size of all drivers list
        if (allDrivers != null) {
            for (Driver d : allDrivers) {
                if (((orderHours + d.getWorkHours()) <= MAX_WORK_HOURS_PER_MONTH) &
                        (d.getState().equals(DriverState.WORK)) & (d.getStatusDriver().equals(DriverStatus.FREE))) {
                    appropriateDrivers.add(d);
                }
            }
        }
        return appropriateDrivers;
    }

    /**
     * Checks whether today is first day of month. If so then work hours of driver will be zero.
     */
    @Transactional
    private void checkFirstMonthDay() {
        if ((new Date().getDate()) == 1) {
            List<Driver> allDrivers = getAll();
            if (allDrivers != null) {
                for (Driver d : allDrivers) {
                    d.setWorkHours(0);
                    update(d);
                }
            }
        }
    }

    /**
     * Gets selected drivers.
     *
     * @param drivers
     * @param selectedDriversID
     * @return selected drivers list
     */
    @Override
    public List<Driver> getSelectedDrivers(List<Driver> drivers, String[] selectedDriversID) {

        int[] driversID = new int[selectedDriversID.length];

        List<Driver> selectedDrivers = new ArrayList<>();

        for (int i = 0; i < selectedDriversID.length; i++) {
            driversID[i] = Integer.valueOf(selectedDriversID[i]) - 1;
        }

        for (int i = 0; i < driversID.length; i++) {
            selectedDrivers.add(drivers.get(driversID[i]));
        }

        return selectedDrivers;
    }

    /**
     * Changes driver's status to status in parameter.
     *
     * @param selectedDrivers
     * @param driverStatus
     */
    @Override
    @Transactional
    public void changeDriversStatuses(List<Driver> selectedDrivers, DriverStatus driverStatus) {
        for (Driver d : selectedDrivers) {
            d.setStatusDriver(driverStatus);
            update(d);
        }
        logger.info("Selected drivers' statuses are changed to " + driverStatus);
    }

    /**
     * Changes driver's state to state in parameter.
     *
     * @param selectedDrivers
     * @param driverState
     */
    @Override
    @Transactional
    public void changeDriversStates(List<Driver> selectedDrivers, DriverState driverState) {
        for (Driver d : selectedDrivers) {
            d.setState(driverState);
            update(d);
        }
        logger.info("Selected drivers' states are changed to " + driverState);
    }

    /**
     * Gets drivers related to orderID.
     *
     * @param orderID
     * @return list of drivers
     */
    @Override
    @Transactional
    public List<Driver> getBusyDrivers(int orderID) {

        logger.info("Incoming order: " + orderID);

        List<Driver> allDrivers = driverDao.getAll();
        List<Driver> busyDrivers = new ArrayList<>(allDrivers.size());

        for (Driver d : allDrivers) {
            Order currentOrder = d.getCurrentOrder();
            if (currentOrder != null) {
                Integer currentOrderID = d.getCurrentOrder().getIdOrder();
                if (currentOrderID != null) {
                    if (currentOrderID.equals(orderID)) {
                        logger.info("Driver: " + d + ", currentOrder: " + currentOrderID);
                        busyDrivers.add(d);
                    }
                }
            }
        }
        return busyDrivers;
    }

    /**
     * Deletes links related with drivers.
     *
     * @param busyDrivers
     * @param order
     */
    @Override
    @Transactional
    public void breakLinks(List<Driver> busyDrivers, Order order) {

        logger.info("Incoming order: " + order.getNumber());

        if (busyDrivers != null) {
            for (Driver d : busyDrivers) {
                logger.info("Setting current order to null to driver: " + d);
                d.setCurrentOrder(null);
                update(d);
            }
            logger.info("All links between " + order.getNumber() + " and chosen drivers are deleted");
        }
    }

    /**
     * Sets work hours to drivers who was appointed to execute the order.
     *
     * @param selectedDrivers
     * @param totalHoursAmount
     */
    @Override
    @Transactional
    public void setWorkHours(List<Driver> selectedDrivers, Integer totalHoursAmount) {
        if (selectedDrivers!=null){
            for (Driver driver: selectedDrivers){
                driver.setWorkHours(totalHoursAmount);
                update(driver);
            }
        }
    }
}
