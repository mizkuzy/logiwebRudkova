package entities;

import javax.persistence.*;

@Entity
@Table(name = "ROUTE_LABELS")
@NamedQuery(name = "RouteLabel.getAll", query = "SELECT rl FROM RouteLabel rl")
public class RouteLabel {

    public RouteLabel() {
    }

    public RouteLabel(String label) {
        this.label = label;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "label")
    private String label;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
