package ru.tsystems.logiweb.entities;

import ru.tsystems.logiweb.entities.statusesAndStates.DriverState;
import ru.tsystems.logiweb.entities.statusesAndStates.DriverStatus;

import javax.persistence.*;

@Entity
@Table(name = "DRIVERS")
@NamedQuery(name = "Driver.getAll", query = "SELECT d FROM Driver d")
public class Driver {

    public Driver() {
    }

    public Driver(String name, String surname) {
        this.name = name;
        this.surname = surname;
        this.workHours = 0;
        this.state = DriverState.WORK;
        this.statusDriver = DriverStatus.FREE;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @OneToOne(mappedBy = "driverFK", cascade = CascadeType.ALL)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "current_Order")
    private Order currentOrder;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "workHours")
    private Integer workHours;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private DriverState state;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private DriverStatus statusDriver;

    @Override
    public String toString() {
        return name + " " + surname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(Order currentOrder) {
        this.currentOrder = currentOrder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getWorkHours() {
        return workHours;
    }

    public void setWorkHours(Integer workHours) {
        this.workHours = workHours;
    }

    public DriverState getState() {
        return state;
    }

    public void setState(DriverState state) {
        this.state = state;
    }

    public DriverStatus getStatusDriver() {
        return statusDriver;
    }

    public void setStatusDriver(DriverStatus statusDriver) {
        this.statusDriver = statusDriver;
    }
}

