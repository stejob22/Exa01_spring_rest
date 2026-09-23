package stejob.exa01_spring_rest.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import stejob.exa01_spring_rest.pojos.dto.CustomerDto;
import stejob.exa01_spring_rest.services.CustomerService;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/api/custommer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public List<CustomerDto> getAll() {
        return customerService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getById(@PathVariable Long id) {
        return ResponseEntity.of(customerService.getById(id));
    }

    @PostMapping
    public ResponseEntity<CustomerDto> create(@RequestBody CustomerDto customerDto) {
        checkAge(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.save(customerDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> update(@PathVariable Long id, @RequestBody CustomerDto customerDto) {
        checkAge(customerDto);
        return ResponseEntity.of(customerService.update(id, customerDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CustomerDto> deleteById(@PathVariable Long id) {
        return ResponseEntity.of(customerService.deleteById(id));
    }

    private static void checkAge(CustomerDto dto) {
        if (dto.birthday() == null || dto.birthday().isAfter(LocalDate.now().minusYears(12))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Too young");
        }
    }
}
