package stejob.exa01_spring_rest.controller;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stejob.exa01_spring_rest.entities.Customer;
import tools.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/custommer")
@Slf4j
@RequiredArgsConstructor
public class CustomerController {


    private final ObjectMapper objectMapper;
    private List<Customer> customers = new ArrayList<>();


    @PostConstruct
    public void loadJsonFile(){

        InputStream jsonStream = CustomerController.class.getResourceAsStream("/customers.json");
        customers = objectMapper.readerForListOf(Customer.class).readValue(jsonStream);
        System.out.println(customers);
    }


}
