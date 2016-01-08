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
        logger.info("Rout " + city1 + " - " + city2);
        return routDAO.getByCities(city1, city2);
    }

    /**
     * Collects all cities and return list of all cities
     *
     * @return citiesList
     */
    @Override
    public List<String> getCities() {
        final int CITIES_AMOUNT = 11;
        List<String> cities = new ArrayList<>(CITIES_AMOUNT);
        cities.add("Saint-Petersburg");
        cities.add("Veliky_Novgorod");
        cities.add("Pskov");
        cities.add("Petrozavodsk");
        cities.add("Arhangelsk");
        cities.add("Vologda");
        cities.add("Siktivkar");
        cities.add("Naryan-Mar");
        cities.add("Murmansk");
        cities.add("Kaliningrad");
        cities.add("Cherepovec");
        return cities;
    }

    @Override
    public List<String> getCitiesForSPb() {
        final int CITIES_AMOUNT = 10;
        List<String> cities = new ArrayList<>(CITIES_AMOUNT);
        cities.add("Veliky_Novgorod");
        cities.add("Pskov");
        cities.add("Petrozavodsk");
        cities.add("Arhangelsk");
        cities.add("Vologda");
        cities.add("Siktivkar");
        cities.add("Naryan-Mar");
        cities.add("Murmansk");
        cities.add("Kaliningrad");
        cities.add("Cherepovec");
        return cities;
    }

    @Override
    public List<String> getCitiesForVelikyNovgorod() {
        final int CITIES_AMOUNT = 3;
        List<String> cities = new ArrayList<>(CITIES_AMOUNT);
        cities.add("Saint-Petersburg");
        cities.add("Pskov");
        cities.add("Kaliningrad");
        return cities;
    }

    @Override
    public List<String> getCitiesForPskov() {
        final int CITIES_AMOUNT = 3;
        List<String> cities = new ArrayList<>(CITIES_AMOUNT);
        cities.add("Saint-Petersburg");
        cities.add("Veliky_Novgorod");
        cities.add("Kaliningrad");
        return cities;
    }

    @Override
    public List<String> getCitiesForPetrozavodsk() {
        final int CITIES_AMOUNT = 2;
        List<String> cities = new ArrayList<>(CITIES_AMOUNT);
        cities.add("Saint-Petersburg");
        cities.add("Murmansk");
        return cities;
    }

    @Override
    public List<String> getCitiesForArhangelsk() {
        final int CITIES_AMOUNT = 3;
        List<String> cities = new ArrayList<>(CITIES_AMOUNT);
        cities.add("Saint-Petersburg");
        cities.add("Naryan-Mar");
        cities.add("Cherepovec");
        return cities;
    }

    @Override
    public List<String> getCitiesForVologda() {
        final int CITIES_AMOUNT = 2;
        List<String> cities = new ArrayList<>(CITIES_AMOUNT);
        cities.add("Saint-Petersburg");
        cities.add("Siktivkar");
        return cities;
    }

    @Override
    public List<String> getCitiesForSiktivkar() {
        final int CITIES_AMOUNT = 2;
        List<String> cities = new ArrayList<>(CITIES_AMOUNT);
        cities.add("Saint-Petersburg");
        cities.add("Vologda");
        return cities;
    }

    @Override
    public List<String> getCitiesForNaryanMar() {
        final int CITIES_AMOUNT = 3;
        List<String> cities = new ArrayList<>(CITIES_AMOUNT);
        cities.add("Saint-Petersburg");
        cities.add("Arhangelsk");
        cities.add("Cherepovec");
        return cities;
    }

    @Override
    public List<String> getCitiesForMurmansk() {
        final int CITIES_AMOUNT = 2;
        List<String> cities = new ArrayList<>(CITIES_AMOUNT);
        cities.add("Saint-Petersburg");
        cities.add("Petrozavodsk");
        return cities;
    }

    @Override
    public List<String> getCitiesForKaliningrad() {
        final int CITIES_AMOUNT = 3;
        List<String> cities = new ArrayList<>(CITIES_AMOUNT);
        cities.add("Saint-Petersburg");
        cities.add("Veliky_Novgorod");
        cities.add("Pskov");
        return cities;
    }

    @Override
    public List<String> getCitiesForCherepovec() {
        final int CITIES_AMOUNT = 3;
        List<String> cities = new ArrayList<>(CITIES_AMOUNT);
        cities.add("Saint-Petersburg");
        cities.add("Arhangelsk");
        cities.add("Naryan-Mar");
        return cities;
    }
}
