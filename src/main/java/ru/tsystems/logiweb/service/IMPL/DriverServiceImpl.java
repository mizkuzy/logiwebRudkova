package ru.tsystems.logiweb.service.IMPL;

import ru.tsystems.logiweb.dao.API.DriverGenericDAO;
import ru.tsystems.logiweb.entities.Driver;
import ru.tsystems.logiweb.entities.statusesAndStates.DriverState;
import ru.tsystems.logiweb.entities.statusesAndStates.DriverStatus;
import ru.tsystems.logiweb.service.API.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.util.calendar.CalendarDate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * An implementation of DriverService API.
 */
@Service("driverService")
public class DriverServiceImpl implements DriverService {

    public static final int MAX_WORK_HOURS_PER_MONTH = 176;

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

    @Override
    @Transactional
    public void changeDriversStatuses(List<Driver> selectedDrivers) {
        for (Driver d :
                selectedDrivers) {
            d.setStatusDriver(DriverStatus.BUSY);
            update(d);
        }
    }

}
