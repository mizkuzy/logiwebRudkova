package ru.tsystems.logiweb.entities;

import ru.tsystems.logiweb.entities.statusesAndStates.RequestStatus;

import javax.persistence.*;

@Entity
@Table(name = "REQUESTS")
@NamedQuery(name = "Request.getAll", query = "SELECT req FROM Request req")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer IdRequest;

    @Enumerated(EnumType.STRING)
    @Column(name = "statusRequest")
    private RequestStatus statusRequest;

    @OneToOne
    @JoinColumn(name = "good")
    private Good goodForRequest;

    @ManyToOne
    @JoinColumn(name = "rout")
    private Rout routForRequest;

    @ManyToOne
    @JoinColumn(name = "current_Order")
    private Order currentOrder;

    public Integer getIdRequest() {
        return IdRequest;
    }

    public void setIdRequest(Integer idRequest) {
        IdRequest = idRequest;
    }

    public RequestStatus getStatusRequest() {
        return statusRequest;
    }

    public void setStatusRequest(RequestStatus statusRequest) {
        this.statusRequest = statusRequest;
    }

    public Good getGoodForRequest() {
        return goodForRequest;
    }

    public void setGoodForRequest(Good goodForRequest) {
        this.goodForRequest = goodForRequest;
    }

    public Rout getRoutForRequest() {
        return routForRequest;
    }

    public void setRoutForRequest(Rout routForRequest) {
        this.routForRequest = routForRequest;
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(Order currentOrder) {
        this.currentOrder = currentOrder;
    }
}