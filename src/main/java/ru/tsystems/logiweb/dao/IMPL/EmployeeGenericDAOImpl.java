package ru.tsystems.logiweb.dao.IMPL;

import org.apache.log4j.Logger;
import ru.tsystems.logiweb.dao.API.EmployeeGenericDAO;
import ru.tsystems.logiweb.entities.Employee;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

/**
 * An implementation of EmployeeGenericDAO API.
 */
@Repository("employeeDAO")
public class EmployeeGenericDAOImpl extends GenericDAOImpl<Employee, Integer> implements EmployeeGenericDAO {

    private Logger logger = Logger.getLogger(EmployeeGenericDAOImpl.class);

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

    /**
     * Get required EmployeeEntity by specified email.
     *
     * @param email
     * @return Employee's instance. //TODO Герман. Можно ли здесь употребить instance?
     */
    @Override
    public Employee getEmployeeByEmail(String email) {
        try {
            Query query = entityManager.createQuery("select emp from Employee emp where emp.email=:email");
            query.setParameter("email", email);
            return (Employee) query.getSingleResult();
        } catch (PersistenceException ex) {
            logger.info("There was NoResultException because of wrong email: " + email);
            throw new NoResultException("Entity with e-mail " + email + "not found ");
        }
    }
}
