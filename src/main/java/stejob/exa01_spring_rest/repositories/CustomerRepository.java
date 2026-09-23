package stejob.exa01_spring_rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import stejob.exa01_spring_rest.pojos.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("SELECT MAX(c.id) FROM Customer c")
    Long findMaxID();
}
