# Desafíos Magneto Mutante

Este proyecto implementa una API REST en Spring Boot para detectar mutantes a partir de secuencias de ADN. La solución incluye una base de datos H2 para almacenar verificaciones.

## Desafíos

### Nivel 1
Programa en JAVA que cumpla con el método pedido por Magneto
### Nivel 2
API REST para detectar mutantes, deployada en [https://parcial-desarrollo-49979-carobolante-2.onrender.com/](https://parcial-desarrollo-49979-carobolante-2.onrender.com/).
- **Endpoint**: `/mutant/`
- **Método**: `POST`
- **Formato del body**:
  ```json
  { "dna": ["ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"] }
### Nivel 3
Expande la API con almacenamiento en base de datos.

- **Método**: `GET`
- **Formato de respuesta**:
  ```json
  {
    "count_mutant_dna": 40,
    "count_human_dna": 100,
    "ratio": 0.4
  }
### TANTO LOS DIAGRAMAS DE SECUENCIA COMO LAS PRUEBAS EN JMETER ESTÁN ADJUNTAS EN EL DOCUMENTO:

```
https://docs.google.com/document/d/1HeQk_rFI0wRMUo6RAI7WchqtpJzbt4v5OQ0mguy2bNQ/edit?tab=t.0
```
