package ru.tsystems.logiweb.entities;

import javax.persistence.Entity;

import ru.tsystems.logiweb.entities.statusesAndStates.*;
import javax.persistence.*;

@Entity
@Table(name = "EMPLOYEES")
@NamedQuery(name = "Employee.getAll", query = "SELECT em FROM Employee em")
public class Employee {

    public Employee() {
    }

    public Employee(String email, String password, POSITION position) {
        this.email = email;
        this.password = password;
        this.position = position;
        //this.personalNumber = ; TODO придумать как  генерировать номер
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer employeeId;

    @Column(name = "personalNumber")
    private Integer personalNumber;

    @OneToOne
    @JoinColumn(name = "driver_FK", unique = true)
    private Driver driverFK;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "POSITION")
    private POSITION position;

    @Override
    public String toString() {
        return "Personal Number= " + this.getPersonalNumber() + "; position= " + this.getPosition();
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(Integer personalNumber) {
        this.personalNumber = personalNumber;
    }

    public Driver getDriverFK() {
        return driverFK;
    }

    public void setDriverFK(Driver driverFK) {
        this.driverFK = driverFK;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public POSITION getPosition() {
        return position;
    }

    public void setPosition(POSITION position) {
        this.position = position;
    }
}



