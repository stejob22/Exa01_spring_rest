package stejob.exa01_spring_rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import stejob.exa01_spring_rest.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
