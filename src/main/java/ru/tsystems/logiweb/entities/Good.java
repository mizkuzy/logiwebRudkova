package ru.tsystems.logiweb.entities;

import ru.tsystems.logiweb.entities.statusesAndStates.GoodsStatus;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "GOODS")
@NamedQuery(name = "Good.getAll", query = "SELECT good FROM Good good")
public class Good implements Serializable {

    public Good() {
        //Does nothing because this class is POJO
        // and it's required to have an empty constructor
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

    @NotNull
    @Size(min = 5, max = 30, message = "Invalid good's name.")
    @Column(name = "name")
    private String name;

    @NotNull
    @Max(50)
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
