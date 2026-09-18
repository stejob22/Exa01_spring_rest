package stejob.exa01_spring_rest.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import stejob.exa01_spring_rest.entities.Customer;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class CustomerControllerTest {

    private MockMvc mockMvc;
    private CustomerController customerController;

    @BeforeEach
    void setUp() {

        ObjectMapper objectMapper = new ObjectMapper();

        customerController = new CustomerController(objectMapper);

        // Damit wir nicht von customers.json abhängig sind
        customerController.setCustomers(new ArrayList<>());

        mockMvc = MockMvcBuilders
                .standaloneSetup(customerController)
                .build();
    }


    @Test
    void testGetAll() throws Exception {

        mockMvc.perform(get("/api/custommer"))
                .andExpect(status().isOk());
    }


    @Test
    void testPost() throws Exception {

        String json = """
                {
                    "id": 1,
                    "firstname": "Max",
                    "lastname": "Mustermann",
                    "email": "max@test.at",
                    "gender": "Male",
                    "birthday": "18-09-2005"
                }
                """;

        mockMvc.perform(post("/api/custommer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstname").value("Max"));
    }


    @Test
    void testGetById() throws Exception {

        Customer customer = new Customer(
                1L,
                "Max",
                "Mustermann",
                "max@test.at",
                "Male",
                LocalDate.of(2005, 9, 18)
        );

        customerController.getCustomers().add(customer);

        mockMvc.perform(get("/api/custommer/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstname").value("Max"));
    }


    @Test
    void testGetByIdNotFound() throws Exception {

        mockMvc.perform(get("/api/custommer/999"))
                .andExpect(status().isNotFound());
    }


    @Test
    void testPut() throws Exception {

        Customer customer = new Customer(
                1L,
                "Max",
                "Alt",
                "alt@test.at",
                "Male",
                LocalDate.of(2005, 9, 18)
        );

        customerController.getCustomers().add(customer);

        String json = """
                {
                    "id": 1,
                    "firstname": "Max",
                    "lastname": "Neu",
                    "email": "neu@test.at",
                    "gender": "Male",
                    "birthday": "18-09-2005"
                }
                """;

        mockMvc.perform(put("/api/custommer/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.lastname").value("Neu"))
                .andExpect(jsonPath("$.email").value("neu@test.at"));
    }
}