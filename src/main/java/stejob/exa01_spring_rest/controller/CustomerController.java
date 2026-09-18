package stejob.exa01_spring_rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stejob.exa01_spring_rest.entities.Customer;
import stejob.exa01_spring_rest.services.CustomerService;

import java.util.List;


@RestController
@RequestMapping("/api/custommer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public List<Customer> getAll() {
        return customerService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getById(@PathVariable Long id) {
        return ResponseEntity.of(customerService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Customer> post(@RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.save(customer));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Customer> deleteById(@PathVariable Long id) {
        return ResponseEntity.of(customerService.deleteById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> put(@PathVariable Long id, @RequestBody Customer customer) {
        return ResponseEntity.of(customerService.update(id, customer));
    }
}
