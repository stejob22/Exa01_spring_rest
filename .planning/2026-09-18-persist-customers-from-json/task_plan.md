# Task Plan: Persist customers from JSON

## Goal
Persist imported customer JSON data in PostgreSQL and make CustomerController depend only on a service layer.

## Next Step
Deliver the completed service-layer refactor.

## Current Phase
Phase 6

## Phases

### Phase 1: Requirements & Discovery
- [x] Understand user intent
- [x] Identify constraints
- [x] Document in findings.md
- **Status:** complete

### Phase 2: Planning & Structure
- [x] Define approach
- [x] Create project structure
- **Status:** complete

### Phase 3: Implementation
- [x] Execute the plan
- [x] Write to files before executing
- **Status:** complete

### Phase 4: Testing & Verification
- [x] Verify requirements met
- [x] Document test results
- **Status:** complete

### Phase 5: Delivery
- [x] Review outputs
- [x] Deliver to user
- **Status:** complete

### Phase 6: Service Layer
- [x] Add CustomerService between controller and repository
- [x] Move update and delete behavior into the service
- [x] Run and document tests
- **Status:** complete

## Decisions Made
| Decision | Rationale |
|----------|-----------|
| Use the project's existing Spring/JPA patterns | Keeps the change minimal and consistent. |
| Extend `JpaRepository<Customer, Long>` without custom methods | Spring Data 4.1.1 already provides all required CRUD queries. |
| Keep JSON loading in a dedicated `ApplicationRunner` | The controller remains independent from the source of initial data. |
| Preserve the existing endpoint paths and response shapes | The request is an internal persistence refactor, not an API redesign. |
| Return domain values and `Optional` from the service | Keeps HTTP concerns in the controller. |

## Errors Encountered
| Error | Resolution |
|-------|------------|
| Baseline Maven compilation fails because the installed JDK does not support Java 25 | Record as an environment constraint; compile with a supported release override if compatible. |
