package stejob.exa01_spring_rest.services;

import org.junit.jupiter.api.Test;
import stejob.exa01_spring_rest.pojos.entities.Customer;
import stejob.exa01_spring_rest.repositories.CustomerRepository;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class CustomerServiceTest {

    @Test
    void updatePersistsExistingCustomer() {
        CustomerRepository customerRepository = mock(CustomerRepository.class);
        CustomerService customerService = new CustomerService(customerRepository);
        Customer existingCustomer = new Customer(
                1L, "Max", "Alt", "alt@test.at", "Male", LocalDate.of(2005, 9, 18));
        Customer changes = new Customer(
                1L, "Max", "Neu", "neu@test.at", "Male", LocalDate.of(2005, 9, 18));

        when(customerRepository.findById(1L)).thenReturn(Optional.of(existingCustomer));
        when(customerRepository.save(existingCustomer)).thenReturn(existingCustomer);

        Optional<Customer> result = customerService.update(1L, changes);

        assertThat(result).contains(existingCustomer);
        assertThat(existingCustomer.getLastname()).isEqualTo("Neu");
        assertThat(existingCustomer.getEmail()).isEqualTo("neu@test.at");
        verify(customerRepository).save(existingCustomer);
    }
}
