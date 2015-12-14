package ru.tsystems.logiweb.service.API;

import ru.tsystems.logiweb.entities.Employee;

public interface EmployeeService extends GenericService<Employee,Integer> {

    Employee getEntityByEmail(String email);

    boolean checkEmailAndPassword(String email, String password);

    Integer getPersonalNumber(String email);
}
