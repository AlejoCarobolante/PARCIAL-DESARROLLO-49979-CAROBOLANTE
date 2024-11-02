package com.example.ParcialDesarrollo;

import Ratio.Stats;
import Services.MatrizADNService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import java.util.Scanner;

@EntityScan(basePackages = "Entities")
@ComponentScan(basePackages = {"Services", "Repositories", "Entities"})
@EnableJpaRepositories(basePackages = "Repositories")
@SpringBootApplication
public class ParcialDesarrolloApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ParcialDesarrolloApplication.class, args);
		MatrizADNService matrizADNService = context.getBean(MatrizADNService.class);
		Scanner scanner = new Scanner(System.in);

		boolean continuar = true;
		while (continuar) {
			System.out.print("Ingrese el tamaño de la matriz NxN (n): ");
			int n = scanner.nextInt();
			scanner.nextLine();

			String[] dna = new String[n];
			System.out.println("Ingrese " + n + " filas de ADN (cada fila debe tener " + n + " caracteres):");

			for (int i = 0; i < n; i++) {
				String input = scanner.nextLine();
				if (input.length() != n || !input.matches("[ATCG]+")) {
					System.out.println("Entrada inválida. La fila debe tener " + n + " caracteres y solo contener A, T, C, G.");
					i--;
				} else {
					dna[i] = input;
				}
			}

			try {
				boolean esMutante = matrizADNService.guardarADN(dna);
				System.out.println("Secuencia de ADN guardada en la base de datos.");
				System.out.println("La secuencia de ADN es " + (esMutante ? "mutante." : "no mutante."));
			} catch (Exception e) {
				System.out.println("No fue posible guardar la secuencia en la base de datos.");
				e.printStackTrace();
			}

			System.out.print("¿Desea ingresar otra matriz? (s/n): ");
			continuar = scanner.nextLine().equalsIgnoreCase("s");
		}

		// Mostrar estadísticas al finalizar
		var stats = matrizADNService.obtenerEstadisticas();
		System.out.println("\nEstadísticas:");
		System.out.println("Total de secuencias mutantes: " + stats.getTotalMutantes());
		System.out.println("Total de secuencias no mutantes: " + stats.getTotalNoMutantes());
		System.out.println("Ratio de mutantes: " + stats.getRatio());

		scanner.close();
	}
}
