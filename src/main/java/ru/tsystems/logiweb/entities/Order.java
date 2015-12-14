package ru.tsystems.logiweb.entities;

import ru.tsystems.logiweb.entities.statusesAndStates.OrderStatus;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ORDERS")
@NamedQuery(name = "Order.getAll", query = "SELECT ord FROM Order ord")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer idOrder;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "currentOrder")//todo Герман. насколько рационально загружать весь список водителей я ХЗ. в какой момент кстати они загружаются?
    private final List<Driver> drivers = new ArrayList<>();

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "currentOrder")
    private final List<Request> requests = new ArrayList<>();

    @Column(name = "number")
    private Integer number;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OrderStatus status;

    @OneToOne
    @JoinColumn(name = "vanNumber")
    private Van van;

    public Integer getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Integer idOrder) {
        this.idOrder = idOrder;
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    public void addDriver(Driver driver){
        this.drivers.add(driver);
    }

    public List<Request> getRequests() {
        return requests;
    }

    public void addRequest (Request request){
        this.requests.add(request);
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Van getVan() {
        return van;
    }

    public void setVan(Van van) {
        this.van = van;
    }
}
