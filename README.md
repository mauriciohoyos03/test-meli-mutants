# Solución Challenge Mutants

## Ténologias utilizadas
- Java 11
- Springboot
- Mongo DB

## Requisitos de Instalación
- Java 11
- Cluster o base de datos mongo db

## Instalación
- Clonar el repositorio o descargar el código fuente
- Modificar o agregar las variables de entorno de la base de datos (SPRING_DATA_MONGO_URI, SPRING_DATA_MONGO_DATABASE)

- Generar artefacto con maven
```cmd
mvn clean package
```
- Ubicar el artefacto y Ejecutar la applicacion
```cmd
java -jar mutants-0.0.1-SNAPSHOT.jar
```

## Api
### Validar secuencia dna
* Path: /api/v1/mutant/
* Method: Post
* Body:
 ```cmd
{
  "dna": ["ATGCGA","CAGTGC","TTATGT","AGAAGG",CCCCTA","TCACTG"
  ]
}
```
### Obtener resumen de validaciones 
* Path: /api/v1/stats
* Method: Get
* Response:
 ```cmd
{
  "count_mutant_dna": 40,
  "count_human_dna": 60,
  "ratio": 0.4
}
```

