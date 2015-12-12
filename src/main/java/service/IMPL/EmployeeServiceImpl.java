package service.IMPL;

import dao.API.EmployeeGenericDAO;
import dao.IMPL.EmployeeGenericDAOImpl;
import entities.Employee;
import service.API.EmployeeService;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeGenericDAO employeeDAO = new EmployeeGenericDAOImpl();

    @Override
    public void create(Employee entity) {
        employeeDAO.delete(entity);
    }

    @Override
    public void update(Employee entity) {
        employeeDAO.update(entity);
    }

    @Override
    public void delete(Employee entity) {
        employeeDAO.delete(entity);
    }

    @Override
    public Employee read(Integer id) {
        return employeeDAO.read(id);
    }

    @Override
    public Employee getEntityByEmail(String email) {
        return employeeDAO.getEmployeeByEmail(email);
    }

    @Override
    public List getAll() {
        return employeeDAO.getAll();
    }

    @Override
    public boolean checkEmailAndPassword(String email, String password) {
        Employee employee = employeeDAO.getEmployeeByEmail(email);
        if ((employee.getEmail().equals(email)) & (employee.getPassword().equals(password))) {
            return true;
        }
        return false;
    }

    @Override
    public Integer getPersonalNumber(String email) {
        Employee employee = getEntityByEmail(email);
        return employee.getPersonalNumber();
    }
}
