package stejob.exa01_spring_rest.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stejob.exa01_spring_rest.pojos.entities.Customer;
import stejob.exa01_spring_rest.repositories.CustomerRepository;
import stejob.exa01_spring_rest.services.CustomerService;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/custommer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerRepository customerRepository;

    @GetMapping
    public List<Customer> getAll() {
        return customerService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getById(@PathVariable Long id) {
        return ResponseEntity.of(customerService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Customer> create(@RequestBody Customer customer) {
        Long newId = customerRepository.findMaxID() + 1;
        customer.setId(newId);
        customerRepository.save(customer);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(customer);
//        return ResponseEntity.ok(customerService.save(customer));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> update(@PathVariable Long id, @RequestBody Customer customer) {
        Optional<Customer> customerOpt = customerRepository.findById(id);
        if (customerOpt.isPresent()) {
            customer.setId(id);
            customerRepository.save(customer);
            return ResponseEntity.ok(customer);
        }
        return ResponseEntity.of(customerService.update(id, customer));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Customer> deleteById(@PathVariable Long id) {
        return ResponseEntity.of(customerService.deleteById(id));
    }


}