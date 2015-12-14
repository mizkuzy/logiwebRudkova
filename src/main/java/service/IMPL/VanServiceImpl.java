package service.IMPL;

import dao.API.VanGenericDAO;
import dao.IMPL.VanGenericDAOImpl;
import entities.Van;
import entities.statusesAndStates.VanState;
import entities.statusesAndStates.VanStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.API.VanService;

import java.util.ArrayList;
import java.util.List;

/**
 * An implementation of VanService API.
 */
@Service("vanService")
public class VanServiceImpl implements VanService {

    @Autowired
    private VanGenericDAO vanDAO;

    /**
     * Create required entity.
     *
     * @param entity
     */
    @Override
    @Transactional
    public void create(Van entity) {
        vanDAO.create(entity);
    }

    /**
     * Read required entity.
     *
     * @param id
     * @return
     */
    @Override
    @Transactional
    public Van read(Integer id) {
        return vanDAO.read(id);
    }

    /**
     * Update required entity.
     *
     * @param entity
     */
    @Override
    @Transactional
    public void update(Van entity) {
        vanDAO.update(entity);
    }

    /**
     * Delete required entity.
     *
     * @param entity
     */
    @Override
    @Transactional
    public void delete(Van entity) {
        vanDAO.delete(entity);
    }

    /**
     * Get list of required entities.
     *
     * @return list of entities
     */
    @Override
    @Transactional
    public List getAll() {
        return vanDAO.getAll();
    }

    /**
     * Get list of vans with specialized routLabel
     *
     * @param routLabelType
     * @return list of appropriate vans
     */
    //TODO вообще говоря можно хранить сразу типизированные Фуры
    @Override
    public List<Van> getAppropriateVans(String routLabelType) {
        List<Van> vans = vanDAO.getAll();
        List<Van> appropriateVans = new ArrayList<>();
        for (Van van : vans) {
            if ((van.getRoutLabelForVan().getLabel().equals(routLabelType)) & (van.getStatusVan().equals(VanStatus.WAIT)) & (van.getStateVan().equals(VanState.OK))) {
                appropriateVans.add(van);
            }
        }
        return appropriateVans;
    }
}
