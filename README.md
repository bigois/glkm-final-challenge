# GLKM Final Challenge

Final project for FIAP's postgraduate program in Java Architecture and Development. The application represents an e-commerce scenario composed of catalog, inventory, cart, payment, authentication, and API Gateway services.

## Purpose

The project explores a distributed architecture with Spring Boot, HTTP communication between services, and MySQL persistence. The payment module is organized around Clean Architecture (also known as hexagonal architecture) concepts, separating domain, ports, and adapters.

## Architecture

The repository does not have a single root Maven build. Each service directory is an independent Spring Boot application.

```text
Client
  |
  v
Gateway (8080)
  ├── Products (8081) --> Stock (8082)
  ├── Cart (8083)
  ├── Payment (8084)
  └── Authenticate (8089)

Services with persistence --> MySQL (3306)
```

| Service | Port | Responsibility |
| --- | ---: | --- |
| `Gateway` | 8080 | Exposes product, cart, and payment routes and integrates the internal services. |
| `Products` | 8081 | Manages the product catalog. |
| `Stock` | 8082 | Manages product inventory. |
| `Cart` | 8083 | Manages carts and their items. |
| `Payment` | 8084 | Manages accounts and payment transfers. |
| `Authenticate` | 8089 | Registers users, authenticates requests, and verifies JWT tokens. |

### Clean Architecture in Payment

`Payment` is the module that most closely follows the Clean Architecture proposal:

```text
Payment/src/main/java/br/com/fiap/paymentapi/
├── domain/          # domain models and business rules
├── port/in/         # input contracts for use cases
├── adapter/in/      # HTTP controllers
├── adapter/out/     # MySQL persistence
└── infrastructure/  # DTOs and mappers
```

This organization separates HTTP input, domain rules, and persistence responsibilities. The implementation still keeps Spring dependencies and concrete repositories in domain classes; therefore, it is Clean Architecture-inspired rather than a strict, framework-isolated implementation.

The other modules use a more conventional layered architecture, primarily organized into `controllers`, `services`, `repositories`, `entities`, `dtos`, and `mappers`.

## Technologies

- Java 17
- Spring Boot
- Spring Web and Spring WebFlux
- Spring Data JPA
- Spring Security and JWT
- MySQL 8
- Maven
- Docker Compose

## Running locally

### Prerequisites

- JDK 17
- Maven, to run every module
- Docker and Docker Compose, to start MySQL
- An `APP_SECRET` environment variable for the MySQL password and JWT signing secret

Set it permanently for your Windows user:

```powershell
[Environment]::SetEnvironmentVariable("APP_SECRET", "your-strong-secret", "User")
```

Restart the terminal after setting it. For the current PowerShell session only:

```powershell
$env:APP_SECRET = "your-strong-secret"
```

The same value is used by MySQL and the authentication service. Choose a strong value and do not commit it to the repository.

### Database

The `docker-compose.yaml` file defines the MySQL service used by the application. Start only the database with:

```bash
docker compose up -d mysql
```

The applications use the `fiapsmart` database, as configured in their respective `application.properties` files.

### Services

In separate terminals, enter each service directory and run:

```bash
mvn spring-boot:run
```

For example, to start the Gateway:

```bash
cd Gateway
mvn spring-boot:run
```

`Gateway`, `Products`, `Stock`, and `Cart` also include the Maven Wrapper. On Windows, you can use:

```powershell
.\mvnw.cmd spring-boot:run
```

To use the centralized routes, start the Gateway and every service required by that route. Inter-service URLs are currently configured with `localhost`.

## Main endpoints

The Gateway centralizes public routes for products, carts, and payments:

| Resource | Gateway base path |
| --- | --- |
| Products | `/api/v1/products` |
| Cart | `/api/v1/cart` |
| Payments | `/api/v1/payments` |

The authentication service directly exposes `/auth` and `/users` on port 8089.

## Notes

- `docker-compose.yaml` also contains a Cart container configuration based on generated artifacts in `out/`; the other services are not defined in that Compose file.
- Database passwords and the JWT signing secret are read from `APP_SECRET`. For production use, provide it through a secrets manager.
- Services can be built and tested individually with `mvn test` from their respective directories.

## License

This project is licensed under the [MIT License](LICENSE).
