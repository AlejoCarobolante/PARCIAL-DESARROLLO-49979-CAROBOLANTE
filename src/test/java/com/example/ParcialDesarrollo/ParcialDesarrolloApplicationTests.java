package com.example.ParcialDesarrollo;

import Services.MatrizADNService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ParcialDesarrolloApplicationTests {

	@Test
	void contextLoads() {
	}


	@Autowired
	MatrizADNService matrizADNService;

	@Test
	public void testRows(){
		String[] dna = {"AAAATG","TGCAGT","GCTTCC","CCCCTG","GTAGTC","AGTCAC"};
		assertTrue(matrizADNService.isMutant(dna));
	}

	@Test
	public void testDiagonal(){
		String[] dna = {"AGACTG", "TACAGT", "GCAGCC", "TTGATG", "GTAGTC", "AGTCAA"};
		assertTrue(matrizADNService.isMutant(dna));
	}
	@Test
	public void testColumn() {
		String[] dna = {"AGAATG", "TGCAGT", "GCTTCC", "GTCCTC", "GTAGTC", "GGTCAC"};
		assertTrue(matrizADNService.isMutant(dna));
	}

	@Test
	public void testMainDiagonals() {
		String[] dna = {"AGAATG", "TACAGT", "GCAGCC", "TTGATG", "GTAGTC", "AGTCAA"};
		assertTrue(matrizADNService.isMutant(dna));
	}

	@Test
	public void testSecondaryLeftDiagonals() {
		String[] dna = {"ATAATG", "GTTAGT", "GGCTCG","TTGATG","GTAGTC", "AGTCAA"};
		assertTrue(matrizADNService.isMutant(dna));
	}

	@Test
	public void testSecondaryRightDiagonals() {
		String[] dna = {"ATAATG", "GTATGA", "GCTTAG", "TTTAGG", "GTAGTC", "AGTCAA"};
		assertTrue(matrizADNService.isMutant(dna));
	}

	@Test
	public void testTertiaryLeftDiagonals() {
		String[] dna = {"ATGATG", "GTAGTA","CCTTGG", "TCTAGG", "GGCGTC", "AGTCAA" };
		assertTrue(matrizADNService.isMutant(dna));
	}

	@Test
	public void testTertiaryRightDiagonals() {
		String[] dna = {"ATGATG", "GTATTA", "AATTGG", "ACTAGT", "GGAGTC", "AGGCAA"};
		assertTrue(matrizADNService.isMutant(dna));
	}

	@Test
	public void testNonMutant() {
		String[] dna = {"ATGATG", "GTCTTA", "AATTGG", "ACTAGT", "GGATTC", "AGGCAA"};
		assertFalse(matrizADNService.isMutant(dna));
	}
	@Test
	public void testMutant4(){
		String[] dna = {"TTTTTTTTT","TTTTTTTTT", "TTTTTTTTT", "TTTTTTTTT", "CCGACCAGT","GGCACTCCA", "AGGACACTA", "CAAAGGCAT", "GCAGTCCCC"};
		assertTrue(matrizADNService.isMutant(dna));
	}
}
