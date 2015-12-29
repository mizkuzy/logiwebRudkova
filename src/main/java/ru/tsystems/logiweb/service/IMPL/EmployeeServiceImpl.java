package ru.tsystems.logiweb.service.IMPL;

import org.apache.log4j.Logger;
import ru.tsystems.logiweb.dao.API.EmployeeGenericDAO;
import ru.tsystems.logiweb.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.logiweb.service.API.EmployeeService;

import javax.persistence.NoResultException;
import java.util.List;

/**
 * An implementation of EmployeeService API.
 */
@Service("employeeService") //TODO почему здесь название сервиса с маленькой буквы?
public class EmployeeServiceImpl implements EmployeeService {

    private Logger logger = Logger.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeGenericDAO employeeDAO;

    /**
     * Create required entity.
     *
     * @param entity
     */
    @Override
    @Transactional
    public void create(Employee entity) {
        employeeDAO.delete(entity);
    }

    /**
     * Read required entity.
     *
     * @param id
     * @return
     */
    @Override
    @Transactional
    public Employee read(Integer id) {
        return employeeDAO.read(id);
    }

    /**
     * Update required entity.
     *
     * @param entity
     */
    @Override
    @Transactional
    public void update(Employee entity) {
        employeeDAO.update(entity);
    }

    /**
     * Delete required entity.
     *
     * @param entity
     */
    @Override
    @Transactional
    public void delete(Employee entity) {
        employeeDAO.delete(entity);
    }


    /**
     * Get required EmployeeEntity by specified email.
     *
     * @param email
     * @return Employee's instance.
     * @throws NoResultException
     */
    @Override
    @Transactional
    public Employee getEntityByEmail(String email) throws NoResultException {
        Employee employee;
        try {
            employee = employeeDAO.getEmployeeByEmail(email);
        } catch (NoResultException e) {
            logger.warn("There is no entity with such email. Exception in EmployeeServiceImpl, getEntityByEmail().", e);
            throw new NoResultException();
        }
        return employee;
    }

    /**
     * Get list of required ru.tsystems.logiweb.entities.
     *
     * @return list of ru.tsystems.logiweb.entities
     */
    @Override
    @Transactional
    public List getAll() {
        return employeeDAO.getAll();
    }

    /**
     * Check existence EmployeeEntity by email and password
     *
     * @param email
     * @param password
     * @return boolean
     */
    @Override
    @Transactional
    public boolean checkEmailAndPassword(String email, String password) {

        try {
            Employee employee = employeeDAO.getEmployeeByEmail(email);
            if ((employee.getEmail().equals(email)) & (employee.getPassword().equals(password))) {
                return true;
            }
        } catch (NoResultException e) {
            //TODO Герман. Может как-то по другому обрабатывать ошибки надо?
            logger.info("There was NoResultException because of wrong email: " + email);
            return false;
        }
        logger.info("Wrong password: " + password);
        return false;
    }

    /**
     * Get personal number by email
     *
     * @param email
     * @return
     */
    @Override
    public Integer getPersonalNumber(String email) {
        Employee employee = getEntityByEmail(email);
        return employee.getPersonalNumber();
    }
}
