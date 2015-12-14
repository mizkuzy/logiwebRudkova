package dao.IMPL;

import dao.API.EmployeeGenericDAO;
import entities.Employee;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * An implementation of EmployeeGenericDAO API.
 */
@Repository("employeeDAO")
public class EmployeeGenericDAOImpl extends GenericDAOImpl<Employee, Integer> implements EmployeeGenericDAO {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Get required EmployeeEntity by specified email.
     * @param email
     * @return Employee's instance. //TODO Герман. Можно ли здесь употребить instance?
     */
    @Override
    public Employee getEmployeeByEmail(String email) {
        Query query = entityManager.createQuery("SELECT c FROM Employee c where c.email=:email");
        query.setParameter("email", email);
        return (Employee) query.getSingleResult();
    }
}
