package ru.tsystems.logiweb.service.API;

import ru.tsystems.logiweb.entities.Employee;
import ru.tsystems.logiweb.exceptions.CustomLogiwebException;

public interface EmployeeService extends GenericService<Employee, Integer> {

    Employee getEntityByEmail(String email) throws CustomLogiwebException;

    boolean checkEmailAndPassword(String email, String password);

    Integer getPersonalNumber(String email);
}
