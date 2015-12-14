package ru.tsystems.logiweb.entities;

import ru.tsystems.logiweb.entities.statusesAndStates.VanState;
import ru.tsystems.logiweb.entities.statusesAndStates.VanStatus;

import javax.persistence.*;

@Entity
@Table(name = "VANS")
@NamedQuery(name = "Van.getAll", query = "SELECT v FROM Van v")
public class Van {

    public Van() {
    }

    public Van(Integer driversAmount, Integer capacity) {
        this.driversAmount = driversAmount;
        this.capacity = capacity;
        this.statusVan = VanStatus.WAIT;
        this.stateVan = VanState.OK;
    }

    //TODO обязательно убрать этот констуктор после показа, потому что здесь номер фуры не проверяется
    public Van(String vanNumber, Integer driversAmount, Integer capacity) {
        this.vanNumber = vanNumber;
        this.driversAmount = driversAmount;
        this.capacity = capacity;
        this.statusVan = VanStatus.WAIT;
        this.stateVan = VanState.OK;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer idVan;

    @ManyToOne
    @JoinColumn(name = "routeLabel")
    private RouteLabel routLabelForVan;

    @Column(name = "vanNumber")
    private String vanNumber;

    @Column(name = "driversAmount")
    private Integer driversAmount;

    @Column(name = "capacity")
    private Integer capacity;

    @Enumerated(EnumType.STRING)
    @Column(name = "statusVan")
    private VanStatus statusVan;

    @Enumerated(EnumType.STRING)
    @Column(name = "stateVan")
    private VanState stateVan;

    @OneToOne(mappedBy = "van")
    private Order vanNumberInOrder;

    //TODO добавить в сервисы проверку vanNumber


    @Override
    public String toString() {
        return vanNumber;
    }

    public Integer getIdVan() {
        return idVan;
    }

    public void setIdVan(Integer idVan) {
        this.idVan = idVan;
    }

    public RouteLabel getRoutLabelForVan() {
        return routLabelForVan;
    }

    public void setRoutLabelForVan(RouteLabel routLabelForVan) {
        this.routLabelForVan = routLabelForVan;
    }

    public String getVanNumber() {
        return vanNumber;
    }

    public void setVanNumber(String vanNumber) {
        this.vanNumber = vanNumber;
    }

    public Integer getDriversAmount() {
        return driversAmount;
    }

    public void setDriversAmount(Integer driversAmount) {
        this.driversAmount = driversAmount;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public VanStatus getStatusVan() {
        return statusVan;
    }

    public void setStatusVan(VanStatus statusVan) {
        this.statusVan = statusVan;
    }

    public VanState getStateVan() {
        return stateVan;
    }

    public void setStateVan(VanState stateVan) {
        this.stateVan = stateVan;
    }

    public Order getVanNumberInOrder() {
        return vanNumberInOrder;
    }

    public void setVanNumberInOrder(Order vanNumberInOrder) {
        this.vanNumberInOrder = vanNumberInOrder;
    }
}
