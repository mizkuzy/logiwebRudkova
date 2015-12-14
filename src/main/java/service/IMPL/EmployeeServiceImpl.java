package service.IMPL;

import dao.API.EmployeeGenericDAO;
import dao.IMPL.EmployeeGenericDAOImpl;
import entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.API.EmployeeService;

import java.util.List;

/**
 * An implementation of EmployeeService API.
 */
@Service("employeeService") //TODO почему здесь название сервиса с маленькой буквы?
public class EmployeeServiceImpl implements EmployeeService {

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
     * @param email
     * @return Employee's instance. //TODO Герман. Можно ли здесь употребить instance?
     */
    @Override
    @Transactional
    public Employee getEntityByEmail(String email) {
        return employeeDAO.getEmployeeByEmail(email);
    }

    /**
     * Get list of required entities.
     *
     * @return list of entities
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
        Employee employee = employeeDAO.getEmployeeByEmail(email);
        if ((employee.getEmail().equals(email)) & (employee.getPassword().equals(password))) {
            return true;
        }
        return false;
    }

    /**
     * Get personal number of by email
     *
     * @param email
     * @return
     */
    @Override
    //TODO Герман. В этом методе нет обращения к БД, но мы обращаемся к методу,
    // TODO который будет это делать.
    // TODO Значит над этим методом не нужна аннотация @Transactional. ТАК?
    public Integer getPersonalNumber(String email) {
        Employee employee = getEntityByEmail(email);
        return employee.getPersonalNumber();
    }
}
