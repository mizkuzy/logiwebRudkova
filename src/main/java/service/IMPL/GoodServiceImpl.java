package service.IMPL;

import dao.API.GoodGenericDAO;
import dao.IMPL.GoodGenericDAOImpl;
import entities.Good;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.API.GoodService;

import java.util.List;

/**
 * An implementation of GoodService API.
 */
@Service("goodService")
public class GoodServiceImpl implements GoodService {

    @Autowired
    private GoodGenericDAO goodDAO;

    /**
     * Create required entity.
     *
     * @param entity
     */
    @Override
    @Transactional
    public void create(Good entity) {
        goodDAO.create(entity);
    }

    /**
     * Read required entity.
     *
     * @param id
     * @return
     */
    @Override
    @Transactional
    public Good read(Integer id) {
        return goodDAO.read(id);
    }

    /**
     * Update required entity.
     *
     * @param entity
     */
    @Override
    @Transactional
    public void update(Good entity) {
        goodDAO.update(entity);
    }

    /**
     * Delete required entity.
     *
     * @param entity
     */
    @Override
    @Transactional
    public void delete(Good entity) {
        goodDAO.delete(entity);
    }

    /**
     * Get list of required entities.
     *
     * @return list of entities
     */
    @Override
    @Transactional
    public List getAll() {
        return goodDAO.getAll();
    }
}
