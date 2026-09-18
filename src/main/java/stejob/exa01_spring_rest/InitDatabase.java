package stejob.exa01_spring_rest;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import stejob.exa01_spring_rest.entities.Customer;
import stejob.exa01_spring_rest.repositories.CustomerRepository;
import tools.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitDatabase implements ApplicationRunner {

    private final ObjectMapper objectMapper;
    private final CustomerRepository customerRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        try (InputStream jsonStream = InitDatabase.class.getResourceAsStream("/customers.json")) {
            List<Customer> customers = objectMapper.readerForListOf(Customer.class).readValue(jsonStream);
            customerRepository.saveAll(customers);
        }
    }
}
