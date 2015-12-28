package ru.tsystems.logiweb.entities;

import ru.tsystems.logiweb.entities.statusesAndStates.GoodsStatus;

import javax.persistence.*;

@Entity
@Table(name = "GOODS")
@NamedQuery(name = "Good.getAll", query = "SELECT good FROM Good good")
public class Good {

    public Good() {
    }

    public Good(String name, Integer mass) {
        this.name = name;
        this.mass = mass;
        this.status = GoodsStatus.WAIT;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "id")
    private Integer idGood;

    @Column(name = "number")
    private Integer goodNumber;

    @Column(name = "name")
    private String name;

    @Column(name = "mass")
    private Integer mass;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private GoodsStatus status;

    public Integer getIdGood() {
        return idGood;
    }

    public void setIdGood(Integer idGood) {
        this.idGood = idGood;
    }

    public Integer getGoodNumber() {
        return goodNumber;
    }

    public void setGoodNumber(Integer goodNumber) {
        this.goodNumber = goodNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMass() {
        return mass;
    }

    public void setMass(Integer mass) {
        this.mass = mass;
    }

    public GoodsStatus getStatus() {
        return status;
    }

    public void setStatus(GoodsStatus status) {
        this.status = status;
    }
}
