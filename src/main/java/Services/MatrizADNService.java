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
    public Boolean guardarADN(String[] dna) throws Exception {
        boolean isMutant = isMutant(dna);

        if (isMutant) {
            MatrizADN matrizADN = MatrizADN.builder()
                    .esmutante(true)                              // Indicar que es mutante
                    .secuenciaValida(String.join(",", dna))       // Unir la secuencia ADN en un String
                    .build();
            matrizADNRepository.save(matrizADN);
        }
        // Si no es mutante, no guarda en la base de datos
        return isMutant;
    }

    public static boolean isMutant(String[] dna) {
        int n = dna.length;
        int count = 0;

        // Verificar secuencias en todas las direcciones (horizontal, vertical y diagonal)
        for (int i = 0; i < n; i++) {
            StringBuilder vertical = new StringBuilder();

            for (int j = 0; j < n; j++) {
                // Verificar secuencias horizontales
                count += countSequences(dna[i]);
                if (count >= 2) return true; // Si encontramos 2, retornamos verdadero

                // Verificar secuencias verticales
                vertical.append(dna[j].charAt(i));
                count += countSequences(vertical.toString());
                if (count >= 2) return true; // Si encontramos 2, retornamos verdadero

                // Verificar diagonales (de izquierda a derecha)
                if (i + j < n) {
                    String diagonal1 = (i > 0 && j > 0) ? dna[i - 1].charAt(j - 1) + "" : "";
                    count += countSequences(diagonal1 + dna[i].charAt(j));
                    if (count >= 2) return true; // Si encontramos 2, retornamos verdadero

                    // Verificar diagonales (de derecha a izquierda)
                    String diagonal2 = (i > 0 && j < n - 1) ? dna[i - 1].charAt(n - j) + "" : "";
                    count += countSequences(diagonal2 + dna[i].charAt(n - 1 - j));
                    if (count >= 2) return true; // Si encontramos 2, retornamos verdadero
                }
            }
        }

        return count >= 2; // Verificar si encontramos 2 o más secuencias
    }

    // METODO PARA CONTAR LAS SECUENCIAS DE 4 LETRAS IGUALES
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

}
