package Entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;

@Entity
@Table(name="matrizadn")
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

    @Column(name = "EsMutante")
    public static boolean isMutant(String[] dna) {
        int n = dna.length;
        int count = 0;

        // Verificar secuencias horizontales
        for (String row : dna) {
            count += countSequences(row);
        }

        // Verificar secuencias verticales
        for (int col = 0; col < n; col++) {
            StringBuilder column = new StringBuilder();
            for (String row : dna) {
                column.append(row.charAt(col));
            }
            count += countSequences(column.toString());
        }

        // Verificar secuencias diagonales (de izquierda a derecha)
        for (int i = 0; i < n; i++) {
            StringBuilder diagonal = new StringBuilder();
            for (int j = 0; j < n - i; j++) {
                diagonal.append(dna[j + i].charAt(j));
            }
            count += countSequences(diagonal.toString());

            diagonal = new StringBuilder();
            for (int j = 0; j < n - i; j++) {
                diagonal.append(dna[j].charAt(j + i));
            }
            count += countSequences(diagonal.toString());
        }

        // Verificar secuencias diagonales (de derecha a izquierda)
        for (int i = 0; i < n; i++) {
            StringBuilder diagonal = new StringBuilder();
            for (int j = 0; j < n - i; j++) {
                diagonal.append(dna[j + i].charAt(n - j - 1));
            }
            count += countSequences(diagonal.toString());

            diagonal = new StringBuilder();
            for (int j = 0; j < n - i; j++) {
                diagonal.append(dna[j].charAt(n - j - 1 - i));
            }
            count += countSequences(diagonal.toString());
        }

        return count > 1;
    }

    @Column(name = "Contador")
    private static int countSequences(String sequence) {
        int count = 0;
        for (int i = 0; i < sequence.length() - 3; i++) {
            if (sequence.charAt(i) == sequence.charAt(i + 1) &&
                    sequence.charAt(i) == sequence.charAt(i + 2) &&
                    sequence.charAt(i) == sequence.charAt(i + 3)) {
                count++;
            }
        }
        return count;
    }
}