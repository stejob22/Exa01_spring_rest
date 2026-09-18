package stejob.exa01_spring_rest.controller;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stejob.exa01_spring_rest.entities.Customer;
import tools.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


@Data
@RestController
@RequestMapping("/api/custommer")
@Slf4j
@RequiredArgsConstructor
public class CustomerController {


    private final ObjectMapper objectMapper;
    private List<Customer> customers = new ArrayList<>();


    @GetMapping
    public List<Customer> getAll() {
        return customers;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getById(@PathVariable Long id) {
        return ResponseEntity.of(customers.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst());
    }

    @PostMapping
    public ResponseEntity<Customer> post(@RequestBody Customer customer) {
        customers.add(customer);
        return ResponseEntity.ok(customer);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Customer> deleteById(@PathVariable Long id) {
        return ResponseEntity.of(customers.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> put(@PathVariable Long id, @RequestBody Customer customer) {
        return customers.stream().filter(c -> c.getId().equals(id)).findFirst().map(existingCustomer -> {
            existingCustomer.setFirstname(customer.getFirstname());
            existingCustomer.setLastname(customer.getLastname());
            existingCustomer.setEmail(customer.getEmail());
            return ResponseEntity.ok(existingCustomer);
        }).orElse(ResponseEntity.notFound().build());
    }

    @PostConstruct
    public void loadJsonFile() {

        InputStream jsonStream = CustomerController.class.getResourceAsStream("/customers.json");
        customers = objectMapper.readerForListOf(Customer.class).readValue(jsonStream);
        System.out.println(customers);
    }


}
