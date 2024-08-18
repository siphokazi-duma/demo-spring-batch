# demo-spring-batch

`demo-spring-batch` is a Spring Boot application that demonstrates how to use Spring Batch for processing CSV files. 

## What is Spring Batch?

Spring Batch is a framework designed for processing large volumes of data efficiently. It provides reusable components for handling batch processing tasks such as reading, processing, and writing data. Key features include:

- **Chunk-Oriented Processing**: Processes data in chunks rather than in one go, enhancing performance and reliability.
- **ItemReader, ItemProcessor, and ItemWriter**: Components that handle reading data from sources, processing it, and writing the results to destinations.

## Project Overview

This demo project showcases a simple use case where:

1. **Reading Data**: The application reads records from a CSV file using Spring Batch’s `FlatFileItemReader`.
2. **Processing Data**: Each record is processed by an `ItemProcessor`, allowing for business logic to be applied.
3. **Writing Data**: The processed records are written to a new CSV file using `FlatFileItemWriter`.

## Getting Started

1. **Build the Project**: Run `mvn clean install` to build the project.
2. **Run the Application**: Use `mvn spring-boot:run` to start the application.

The input CSV is placed in the `src/main/resources` directory as `coffee-list.csv.

## License

This project is not licensed.
