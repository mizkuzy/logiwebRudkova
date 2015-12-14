package ru.tsystems.logiweb.dao.API;

import ru.tsystems.logiweb.entities.Employee;

public interface EmployeeGenericDAO extends GenericDAO<Employee, Integer>{
    Employee getEmployeeByEmail(String name);
}
