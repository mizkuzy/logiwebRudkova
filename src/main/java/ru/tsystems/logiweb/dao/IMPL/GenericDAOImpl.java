package ru.tsystems.logiweb.dao.IMPL;

import ru.tsystems.logiweb.dao.API.GenericDAO;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.PersistenceException;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * A generic implementation of DAO API (CRUD + getAll).
 */
public abstract class GenericDAOImpl<E, K> implements GenericDAO<E, K> {

    private Logger logger = Logger.getLogger(GenericDAOImpl.class);

    protected Class<E> entityDAOType;

    @SuppressWarnings("unchecked")
    public GenericDAOImpl() {
        entityDAOType = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

    /**
     * Create required entity.
     *
     * @param entity
     * @throws PersistenceException
     */

    //TODO Герман. Что делать с исключениями? Нужно ли создавать свои или достаточно залогировать?
    @Override
    public void create(E entity) throws PersistenceException {
        try {
            entityManager.persist(entity);
        } catch (PersistenceException e) {
            //TODO Герман. Какой тут тип логирования? error или warn?
            logger.error("Creation is failed. Exception in GenericDAOImpl, create().", e);
            throw new PersistenceException(e);
            //TODO ОТЛОВИТЬ В СЕРВИСАХ.
            //TODO СПРОСИТЬ ЗАЧЕМ ПРОБРАСЫВАТЬ НАВЕРХ
        }
    }

    /**
     * Read required entity.
     *
     * @param id
     * @return entity
     * @throws PersistenceException
     */
    @Override
    public E read(K id) throws PersistenceException {
        try {
            return (E) entityManager.find(entityDAOType, id);
        } catch (PersistenceException e) {
            logger.error("Reading is failed. Exception in GenericDAOImpl, read().", e);
            throw new PersistenceException(e);
        }
    }

    /**
     * Update required entity.
     *
     * @param entity
     * @throws PersistenceException
     */
    @Override
    public void update(E entity) throws PersistenceException {
        try {
            entityManager.merge(entity);
        } catch (PersistenceException e) {
            logger.error("Update is failed. Exception in GenericDAOImpl, update().", e);
            throw new PersistenceException(e);
        }
    }

    /**
     * Delete required entity.
     *
     * @param entity
     * @throws PersistenceException
     */
    @Override
    public void delete(E entity) throws PersistenceException {
        try {
            entityManager.remove(entityManager.merge(entity));
        } catch (PersistenceException e) {
            logger.error("Deletion is failed! Exception in GenericDAOImpl, update().", e);
            throw new PersistenceException(e);
        }
    }


    /**
     * Get list of required ru.tsystems.logiweb.entities.
     *
     * @return list of ru.tsystems.logiweb.entities
     * @throws PersistenceException
     */
    @Override
    public List<E> getAll() throws PersistenceException {
        try {
            return entityManager.createNamedQuery(entityDAOType.getSimpleName() + ".getAll", entityDAOType).getResultList();
        } catch (PersistenceException e){
            logger.error("Getting all required ru.tsystems.logiweb.entities is failed! Exception in GenericDAOImpl, getAll().", e);
            throw new PersistenceException(e);
        }

    }
}
