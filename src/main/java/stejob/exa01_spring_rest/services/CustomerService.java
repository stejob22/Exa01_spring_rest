package stejob.exa01_spring_rest.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import stejob.exa01_spring_rest.entities.Customer;
import stejob.exa01_spring_rest.repositories.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getById(Long id) {
        return customerRepository.findById(id);
    }

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public Optional<Customer> deleteById(Long id) {
        return customerRepository.findById(id).map(customer -> {
            customerRepository.delete(customer);
            return customer;
        });
    }

    public Optional<Customer> update(Long id, Customer customer) {
        return customerRepository.findById(id).map(existingCustomer -> {
            existingCustomer.setFirstname(customer.getFirstname());
            existingCustomer.setLastname(customer.getLastname());
            existingCustomer.setEmail(customer.getEmail());
            return customerRepository.save(existingCustomer);
        });
    }
}
