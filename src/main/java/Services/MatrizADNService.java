package Services;

import Entities.MatrizADN;
import Ratio.Stats;
import Repositories.MatrizADNRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatrizADNService {

    @Autowired
    private MatrizADNRepository matrizADNRepository;

    private static int contadorMatrices = 0; // Contador de matrices añadidas

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
    public Boolean guardarADN(String[] dna) throws Exception {
        boolean isMutant = isMutant(dna);

        MatrizADN matrizADN = MatrizADN.builder()
                .secuenciaValida(String.join(",", dna)) // Unir la secuencia ADN en un String
                .esmutante(isMutant)                      // Indicar si es mutante
                .build();

        matrizADNRepository.save(matrizADN); // Guardar la matriz en la base de datos
        contadorMatrices++; // Incrementar el contador de matrices añadidas

        System.out.println("Matriz añadida. Total matrices añadidas: " + contadorMatrices);

        return isMutant;
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
        return count >= 2; // Verificar si encontramos 2 o más secuencias
    }

    // Método para contar las secuencias de 4 letras iguales
    private static int countSequences(String sequence) {
        int count = 0;
        int currentCount = 1;

        for (int i = 1; i < sequence.length(); i++) {
            if (sequence.charAt(i) == sequence.charAt(i - 1)) {
                currentCount++;
                if (currentCount == 4) {
                    count++;
                }
            } else {
                currentCount = 1;
            }
        }
        return count;
    }

    // Método para obtener las estadísticas de mutantes y no mutantes
    public Stats obtenerEstadisticas() {
        long totalMutantes = matrizADNRepository.countByEsmutante(true);
        long totalNoMutantes = matrizADNRepository.countByEsmutante(false);
        double ratio = totalNoMutantes == 0 ? 0 : (double) totalMutantes / contadorMatrices;

        return new Stats(totalMutantes, totalNoMutantes, ratio);
    }

    // Método para obtener el contador de matrices añadidas
    public int getContadorMatrices() {
        return contadorMatrices;
    }
}
