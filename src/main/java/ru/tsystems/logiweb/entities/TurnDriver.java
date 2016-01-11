package ru.tsystems.logiweb.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "TURNS_DRIVERS")
public class TurnDriver implements Serializable {

    public TurnDriver() {
        //Does nothing because this class is POJO
        // and it's required to have an empty constructor
    }

    public TurnDriver(Integer driverNumber, LocalDateTime beginTurn) {
        this.driverNumber = driverNumber;
        this.beginTurn = beginTurn;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "driver_number")
    private Integer driverNumber;

    @Column(name = "begin_turn")
    private LocalDateTime beginTurn;

    @Column(name = "end_turn")
    private LocalDateTime endTurn;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDriverNumber() {
        return driverNumber;
    }

    public void setDriverNumber(Integer driverNumber) {
        this.driverNumber = driverNumber;
    }

    public LocalDateTime getBeginTurn() {
        return beginTurn;
    }

    public void setBeginTurn(LocalDateTime beginTurn) {
        this.beginTurn = beginTurn;
    }

    public LocalDateTime getEndTurn() {
        return endTurn;
    }

    public void setEndTurn(LocalDateTime endTurn) {
        this.endTurn = endTurn;
    }
}
