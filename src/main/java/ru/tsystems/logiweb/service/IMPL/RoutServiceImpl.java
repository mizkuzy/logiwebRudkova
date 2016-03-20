package ru.tsystems.logiweb.service.IMPL;

import org.apache.log4j.Logger;
import ru.tsystems.logiweb.dao.API.RoutGenericDAO;
import ru.tsystems.logiweb.entities.Rout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.logiweb.service.API.RoutService;

import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * An implementation of RoutService API.
 */
@Service("routService")
public class RoutServiceImpl implements RoutService {

    private Logger logger = Logger.getLogger(RoutServiceImpl.class);

    @Autowired
    private RoutGenericDAO routDAO;

    /**
     * Create required entity.
     *
     * @param entity
     */
    @Override
    @Transactional
    public void create(Rout entity) {
        routDAO.create(entity);
    }

    /**
     * Read required entity.
     *
     * @param id
     * @return
     */
    @Override
    @Transactional
    public Rout read(Integer id) {
        return routDAO.read(id);
    }

    /**
     * Update required entity.
     *
     * @param entity
     */
    @Override
    @Transactional
    public void update(Rout entity) {
        routDAO.update(entity);
    }

    /**
     * Delete required entity.
     *
     * @param entity
     */
    @Override
    @Transactional
    public void delete(Rout entity) {
        routDAO.delete(entity);
    }

    /**
     * Get list of required ru.tsystems.logiweb.entities.
     *
     * @return list of ru.tsystems.logiweb.entities
     */
    @Override
    @Transactional
    public List getAll() {
        return routDAO.getAll();
    }

    /**
     * Get rout by city1 and city2
     *
     * @param city1
     * @param city2
     * @return
     * @throws PersistenceException
     */
    @Override
    public Rout getByCities(String city1, String city2) throws PersistenceException {
        logger.debug("Rout " + city1 + " - " + city2);
        return routDAO.getByCities(city1, city2);
    }

    /**
     * Collects all cities and return list of all cities
     *
     * @return citiesList
     */
    @Override
    public List<String> getCities() {

        List<Rout> routs = routDAO.getAll();
        HashSet<String> citiesSet = new HashSet<>();
        for (Rout r : routs) {
            citiesSet.add(r.getCity1());
        }
        List<String> cities = new ArrayList<>(citiesSet);

        return cities;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getCorrespondingCitiesForCity1(String cityFrom) {
        logger.info("cityFrom" + cityFrom);
        List<String> citiesTo = routDAO.getCorrespondingCitiesForCityFrom(cityFrom);
        return citiesTo;
    }

}
