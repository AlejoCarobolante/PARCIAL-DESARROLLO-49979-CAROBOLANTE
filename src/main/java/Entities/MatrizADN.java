package Entities;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;

import lombok.*;

@Entity
@Table(name = "matrizadn")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Audited
public class MatrizADN {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idadn")
    private long idADN;

    @Column(name = "esmutante", nullable = false)
    private boolean esmutante;

    @Column(name = "secuencia")
    private String secuenciaValida;
}