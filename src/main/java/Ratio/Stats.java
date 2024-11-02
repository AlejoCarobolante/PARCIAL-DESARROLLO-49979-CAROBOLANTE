package Ratio;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "stats")
public class Stats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Getters y Setters
    @Setter
    @Getter
    private long totalMutantes;
    @Setter
    @Getter
    private long totalNoMutantes;
    @Setter
    @Getter
    private double ratio;

    // Constructor vacío requerido por JPA
    public Stats() {}

    // Constructor para crear nuevas instancias de Stats
    public Stats(long totalMutantes, long totalNoMutantes, double ratio) {
        this.totalMutantes = totalMutantes;
        this.totalNoMutantes = totalNoMutantes;
        this.ratio = ratio;
    }


}
