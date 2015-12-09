package entities;

import javax.persistence.*;

@Entity
@Table(name = "ROUTS")
@NamedQuery(name = "Rout.getAll", query = "SELECT rout FROM Rout rout")
public class Rout {

    public Rout() {
    }

    public Rout(String city1, String city2, Integer distance, Integer timeDistance) {
        this.city1 = city1;
        this.city2 = city2;
        this.distance = distance;
        this.timeDistance = timeDistance;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer idRout;

    @ManyToOne
    @JoinColumn(name = "routLabel")
    private RouteLabel routeLabelFK;

    @Column(name = "city1")
    private String city1;

    @Column(name = "city2")
    private String city2;

    @Column(name = "distance")
    private Integer distance;

    @Column(name = "timeDistance")
    private Integer timeDistance;

    public Integer getIdRout() {
        return idRout;
    }

    public void setIdRout(Integer idRout) {
        this.idRout = idRout;
    }

    public RouteLabel getRouteLabelFK() {
        return routeLabelFK;
    }

    public void setRouteLabelFK(RouteLabel routeLabelFK) {
        this.routeLabelFK = routeLabelFK;
    }

    public String getCity1() {
        return city1;
    }

    public void setCity1(String city1) {
        this.city1 = city1;
    }

    public String getCity2() {
        return city2;
    }

    public void setCity2(String city2) {
        this.city2 = city2;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Integer getTimeDistance() {
        return timeDistance;
    }

    public void setTimeDistance(Integer timeDistance) {
        this.timeDistance = timeDistance;
    }
}
