package hiber.model;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Generated;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
@Table(name="cars")
public class Car {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name="model")
    private String model;

    @Column(name="series")
    private int series;

    public Car() {
    }

    public Car(String model, int series) {
        this.model = model;
        this.series = series;
    }

    public String getModel() {
        return model;
    }

    public int getSeries() {
        return series;
    }
}
