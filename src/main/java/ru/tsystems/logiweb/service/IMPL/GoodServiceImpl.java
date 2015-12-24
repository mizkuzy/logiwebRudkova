package ru.tsystems.logiweb.service.IMPL;

import org.apache.log4j.Logger;
import ru.tsystems.logiweb.dao.API.GoodGenericDAO;
import ru.tsystems.logiweb.entities.Good;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.logiweb.service.API.GoodService;

import java.util.List;

/**
 * An implementation of GoodService API.
 */
@Service("goodService")
public class GoodServiceImpl implements GoodService {

    private Logger logger = Logger.getLogger(GoodServiceImpl.class);

    @Autowired
    private GoodGenericDAO goodDAO;

   /* @Autowired
    private GoodService goodService;*/

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
     * Get list of required ru.tsystems.logiweb.entities.
     *
     * @return list of ru.tsystems.logiweb.entities
     */
    @Override
    @Transactional
    public List getAll() {
        return goodDAO.getAll();
    }

    /**
     * Create new good with parameters
     *
     * @param goodsName
     * @param mass
     * @return goodNumber
     */
    @Override
    @Transactional
    public Integer addNewGood(String goodsName, Integer mass) {
        Good good = new Good(goodsName, mass);
        create(good); //TODO EXCEPTION?
        int goodNumber = good.getIdGood();
        good.setGoodNumber(goodNumber);
        update(good); //TODO EXCEPTION?
        logger.info("Good is created. Name: " + goodsName + ". Mass: " + mass + ". GoodNumber: " + goodNumber);
        return goodNumber;
    }
}
