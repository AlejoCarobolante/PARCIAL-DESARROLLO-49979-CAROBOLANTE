package com.example.ParcialDesarrollo;

import Entities.MatrizADN;
import Services.MatrizADNService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.Scanner;

import static Services.MatrizADNService.isMutant;

@EntityScan(basePackages = "Entities")
@SpringBootApplication
public class ParcialDesarrolloApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParcialDesarrolloApplication.class, args);

		Scanner scanner = new Scanner(System.in);

		System.out.print("Ingrese el tamaño de la matriz NxN (n): ");
		int n = scanner.nextInt();
		scanner.nextLine(); // Consumir el salto de línea después de nextInt()

		String[] dna = new String[n];
		System.out.println("Ingrese " + n + " filas de ADN (cada fila debe tener " + n + " caracteres):");

		for (int i = 0; i < n; i++) {
			String input = scanner.nextLine();
			if (input.length() != n || !input.matches("[ATCG]+")) {
				System.out.println("Entrada inválida. La fila debe tener " + n + " caracteres y solo contener A, T, C, G.");
				i--; // Retroceder el contador para que el usuario pueda intentar nuevamente
			} else {
				dna[i] = input;
			}
		}

		boolean isMutant = isMutant(dna);
		System.out.println("¿Es mutante? " + isMutant);

		scanner.close();
	}
}

