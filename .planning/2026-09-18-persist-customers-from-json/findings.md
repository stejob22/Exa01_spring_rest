# Findings & Decisions

## Requirements
- Create a Customer repository with the queries needed by CustomerController.
- Add an ApplicationRunner that imports customer JSON data into PostgreSQL at startup.
- Refactor CustomerController so it does not know whether data originated from JSON.
- Verify the application through existing tests/build.

## Research Findings
- `CustomerController` currently owns a mutable list and loads `/customers.json` in `@PostConstruct`.
- All controller operations are CRUD operations already provided by `JpaRepository`.
- `Customer` is already a JPA entity with a manually supplied JSON id.
- PostgreSQL is configured with `spring.jpa.hibernate.ddl-auto=create`.
- Existing controller tests construct the controller directly and will need a mocked repository instead of mutating its list.
- Local Spring Data JPA 4.1.1 source confirms `JpaRepository` inherits list-based CRUD operations.
- Baseline tests cannot compile under the installed JDK because the POM requests Java 25.
- The available runtime is OpenJDK 17; Spring Boot 4 sources/dependencies are already cached locally.
- Mockito 5.23 is present through the existing Spring test dependencies, so controller tests can mock the repository without adding dependencies.

## Technical Decisions
| Decision | Rationale |
|----------|-----------|
| Inspect before choosing repository signatures/import behavior | Existing endpoints and data shape define the minimum correct API. |
| Assert a positive repository count in the context test | Verifies that startup JSON parsing and PostgreSQL persistence both occurred. |

## Issues Encountered
| Issue | Resolution |
|-------|------------|
| Context7 documentation lookup could not reach the network and hung | Stopped it after 60 seconds; verify against locally resolved Maven dependencies and compilation. |
| `./mvnw test` fails before compiling project code: `release version 25 not supported` | Verify the installed JDK and use a temporary compiler release override for local checks if possible. |

## Resources
- Local Spring Data JPA 4.1.1 source JAR.
- Maven Surefire reports in `target/surefire-reports`.
