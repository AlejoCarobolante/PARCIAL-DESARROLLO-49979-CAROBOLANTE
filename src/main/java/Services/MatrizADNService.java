package Services;
import Entities.MatrizADN;
import Repositories.MatrizADNRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatrizADNService {

    @Autowired
    private MatrizADNRepository matrizADNRepository;

    // Constructor con inyección automática de dependencias
    public MatrizADNService(MatrizADNRepository matrizADNRepository) {
        this.matrizADNRepository = matrizADNRepository;
    }

    @Transactional
    public List<MatrizADN> findall() throws Exception {
        try {
            return matrizADNRepository.findAll();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public MatrizADN save(MatrizADN entity) throws Exception {
        try {
            return matrizADNRepository.save(entity);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    @Transactional
    public MatrizADN guardarADN(String[] dna) throws Exception {
        boolean isMutant = isMutant(dna);

        MatrizADN matrizADN = MatrizADN.builder()
                .esmutante(isMutant)                             // Indicar si es mutante
                .secuenciaValida(String.join(",", dna))           // Unir la secuencia ADN en un String
                .build();
        return save(matrizADN);
    }

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

    @Transactional
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
