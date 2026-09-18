package stejob.exa01_spring_rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import stejob.exa01_spring_rest.repositories.CustomerRepository;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class Exa01SpringRestApplicationTests {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void contextLoads() {
        assertThat(customerRepository.count()).isPositive();
    }

}
