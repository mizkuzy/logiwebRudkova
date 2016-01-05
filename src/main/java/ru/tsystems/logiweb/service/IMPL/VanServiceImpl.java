package ru.tsystems.logiweb.service.IMPL;

import org.apache.log4j.Logger;
import ru.tsystems.logiweb.dao.API.VanDAO;
import ru.tsystems.logiweb.entities.Van;
import ru.tsystems.logiweb.entities.statusesAndStates.VanState;
import ru.tsystems.logiweb.entities.statusesAndStates.VanStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.logiweb.service.API.VanService;

import java.util.ArrayList;
import java.util.List;

/**
 * An implementation of VanService API.
 */
@Service("vanService")
public class VanServiceImpl implements VanService {

    private static final int DRIVERS_CAPACITY_FOR_YELLOW_ROUT = 2;
    private static final int DRIVERS_CAPACITY_FOR_GREEN_ROUT = 3;
    private static final int DRIVERS_CAPACITY_FOR_PURPLE_ROUT = 4;
    private static final int DRIVERS_CAPACITY_FOR_BLUE_ROUT = 3;

    private Logger logger = Logger.getLogger(VanServiceImpl.class);

    @Autowired
    private VanDAO vanDAO;

    /**
     * {@inheritDoc}
     *
     * @param entity
     */
    @Override
    @Transactional
    public void create(Van entity) {
        vanDAO.create(entity);
    }

    /**
     * {@inheritDoc}
     *
     * @param id
     * @return entity
     */
    @Override
    @Transactional
    public Van read(Integer id) {
        return vanDAO.read(id);
    }

    /**
     * {@inheritDoc}
     *
     * @param entity
     */
    @Override
    @Transactional
    public void update(Van entity) {
        vanDAO.update(entity);
    }

    /**
     * {@inheritDoc}
     *
     * @param entity
     */
    @Override
    @Transactional
    public void delete(Van entity) {
        vanDAO.delete(entity);
    }

    /**
     * {@inheritDoc}
     *
     * @return list of entities
     */
    @Override
    @Transactional
    public List getAll() {
        return vanDAO.getAll();
    }

    /**
     * Gets list of vans with specialized routLabel.
     *
     * @param routLabelType
     * @return list of appropriate vans
     */
    //TODO вообще говоря можно хранить сразу типизированные Фуры
    @Override
    public List<Van> getAppropriateVans(String routLabelType) {
        List<Van> vans = vanDAO.getAll();
        List<Van> appropriateVans = new ArrayList<>();
        if (vans != null) {
            for (Van van : vans) {
                if ((van.getRoutLabelForVan().getLabel().equals(routLabelType)) && (van.getStatusVan().equals(VanStatus.WAIT)) && (van.getStateVan().equals(VanState.OK))) {
                    appropriateVans.add(van);
                }
            }
        }
        return appropriateVans;
    }

    /**
     * Gets drivers capacity.
     *
     * @param routLabel
     * @return drivers' capacity
     */
    @Override
    public int getDriversCapacity(String routLabel) {
        int driversCapacity = 0;
        switch (routLabel) {
            case "yellow":
                driversCapacity = DRIVERS_CAPACITY_FOR_YELLOW_ROUT;
                break;
            case "green":
                driversCapacity = DRIVERS_CAPACITY_FOR_GREEN_ROUT;
                break;
            case "purple":
                driversCapacity = DRIVERS_CAPACITY_FOR_PURPLE_ROUT;
                break;
            case "blue":
                driversCapacity = DRIVERS_CAPACITY_FOR_BLUE_ROUT;
                break;
        }
        return driversCapacity;
    }

    /**
     * Gets selected van.
     *
     * @param vans
     * @param idVan
     * @return selected van
     */
    @Override
    public Van getSelectedVan(List<Van> vans, int idVan) {
        return vans.get(idVan);
    }

    /**
     * Changes van's status to required.
     *
     * @param van
     * @param status
     */
    @Override
    @Transactional
    public void changeVanStatus(Van van, VanStatus status) {
        van.setStatusVan(status);
        update(van);
    }
}
