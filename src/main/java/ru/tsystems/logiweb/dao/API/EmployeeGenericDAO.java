package ru.tsystems.logiweb.dao.API;

import ru.tsystems.logiweb.entities.Employee;

import javax.persistence.NoResultException;

public interface EmployeeGenericDAO extends GenericDAO<Employee, Integer>{

    Employee getEmployeeByEmail(String name) throws NoResultException;
}
