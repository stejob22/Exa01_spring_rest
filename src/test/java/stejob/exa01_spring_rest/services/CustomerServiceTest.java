package stejob.exa01_spring_rest.services;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import stejob.exa01_spring_rest.pojos.dto.CustomerDto;
import stejob.exa01_spring_rest.pojos.entities.Customer;
import stejob.exa01_spring_rest.pojos.mapper.CustomerMapper;
import stejob.exa01_spring_rest.repositories.CustomerRepository;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class CustomerServiceTest {

    @Test
    void updatePersistsExistingCustomer() {
        CustomerRepository customerRepository = mock(CustomerRepository.class);
        CustomerService customerService = new CustomerService(customerRepository, Mappers.getMapper(CustomerMapper.class));
        Customer existingCustomer = new Customer(
                1L, "Max", "Alt", "alt@test.at", "Male", LocalDate.of(2005, 9, 18));
        CustomerDto changes = new CustomerDto(1L, "Max", "Neu", LocalDate.of(2005, 9, 18));

        when(customerRepository.findById(1L)).thenReturn(Optional.of(existingCustomer));
        when(customerRepository.save(existingCustomer)).thenReturn(existingCustomer);

        Optional<CustomerDto> result = customerService.update(1L, changes);

        assertThat(result).contains(new CustomerDto(1L, "Max", "Neu", LocalDate.of(2005, 9, 18)));
        assertThat(existingCustomer.getLastname()).isEqualTo("Neu");
        assertThat(existingCustomer.getEmail()).isEqualTo("alt@test.at");
        verify(customerRepository).save(existingCustomer);
    }
}
