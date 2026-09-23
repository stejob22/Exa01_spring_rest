package stejob.exa01_spring_rest.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import stejob.exa01_spring_rest.pojos.dto.CustomerDto;
import stejob.exa01_spring_rest.pojos.entities.Customer;
import stejob.exa01_spring_rest.pojos.mapper.CustomerMapper;
import stejob.exa01_spring_rest.repositories.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public List<CustomerDto> getAll() {
        List<Customer> customer =  customerRepository.findAll();
        return customerMapper.toDtoList(customer);

    }

    public Optional<CustomerDto> getById(Long id) {
        return customerRepository.findById(id).map(customerMapper::toDto);
    }

    public CustomerDto save(CustomerDto customerDto) {
        Customer customer = customerMapper.toEntity(customerDto);
        Long maxId = customerRepository.findMaxID();
        customer.setId(maxId == null ? 1 : maxId + 1);
        return customerMapper.toDto(customerRepository.save(customer));
    }

    public Optional<CustomerDto> deleteById(Long id) {
        return customerRepository.findById(id).map(customer -> {
            customerRepository.delete(customer);
            return customerMapper.toDto(customer);
        });
    }

    public Optional<CustomerDto> update(Long id, CustomerDto customerDto) {
        return customerRepository.findById(id).map(existingCustomer -> {
            existingCustomer.setFirstname(customerDto.firstname());
            existingCustomer.setLastname(customerDto.lastname());
            existingCustomer.setBirthday(customerDto.birthday());
            return customerMapper.toDto(customerRepository.save(existingCustomer));
        });
    }
}
