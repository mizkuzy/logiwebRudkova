package dao.API;

import entities.Employee;

public interface EmployeeGenericDAO extends GenericDAO<Employee, Integer>{
    Employee getEmployeeByEmail(String name);
}
