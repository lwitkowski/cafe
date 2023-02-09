# Cafe

Order anything you want. Anything at all.

# Development

## Prerequisites

- Java 17 SDK

## CLI

- `./mvnw verify` - runs all tests
- `./mvnw clean compiler:compile compiler:testCompile spotbugs:check` - run static code analysis
- `./run"` - compiles and runs the app with sample order input
- `./mvnw package -DskipTests && java -jar target/cafe.jar 'large coffee with extra milk, orange juice, small coffee with special roast, bacon roll'`