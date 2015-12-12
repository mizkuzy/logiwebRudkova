package service.IMPL;

import dao.API.VanGenericDAO;
import dao.IMPL.VanGenericDAOImpl;
import entities.Van;
import entities.statusesAndStates.VanState;
import entities.statusesAndStates.VanStatus;
import service.API.VanService;

import java.util.ArrayList;
import java.util.List;

public class VanServiceImpl implements VanService {

    private VanGenericDAO vanDAO = new VanGenericDAOImpl();

    @Override
    public void create(Van entity) {
        vanDAO.create(entity);
    }

    @Override
    public Van read(Integer id) {
        return vanDAO.read(id);
    }

    @Override
    public void update(Van entity) {
        vanDAO.update(entity);
    }

    @Override
    public void delete(Van entity) {
        vanDAO.delete(entity);
    }

    @Override
    public List getAll() {
        return vanDAO.getAll();
    }

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
