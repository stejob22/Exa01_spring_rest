package stejob.exa01_spring_rest.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import stejob.exa01_spring_rest.entities.Customer;
import stejob.exa01_spring_rest.services.CustomerService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class CustomerControllerTest {

    private MockMvc mockMvc;
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        customerService = mock(CustomerService.class);
        CustomerController customerController = new CustomerController(customerService);

        mockMvc = MockMvcBuilders
                .standaloneSetup(customerController)
                .build();
    }


    @Test
    void testGetAll() throws Exception {
        when(customerService.getAll()).thenReturn(List.of());

        mockMvc.perform(get("/api/custommer"))
                .andExpect(status().isOk());
    }


    @Test
    void testPost() throws Exception {
        when(customerService.save(any(Customer.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

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

        when(customerService.getById(1L)).thenReturn(Optional.of(customer));

        mockMvc.perform(get("/api/custommer/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstname").value("Max"));
    }


    @Test
    void testGetByIdNotFound() throws Exception {
        when(customerService.getById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/custommer/999"))
                .andExpect(status().isNotFound());
    }


    @Test
    void testGetMaxId() throws Exception {
        when(customerService.getMaxId()).thenReturn(Optional.of(new Customer(
                1000L, "Max", "Mustermann", "max@test.at", "Male", LocalDate.of(2005, 9, 18))));

        mockMvc.perform(get("/api/custommer/maxid"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1000));
    }


    @Test
    void testPut() throws Exception {
        when(customerService.update(eq(1L), any(Customer.class)))
                .thenReturn(Optional.of(new Customer(
                        1L,
                        "Max",
                        "Neu",
                        "neu@test.at",
                        "Male",
                        LocalDate.of(2005, 9, 18)
                )));

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

    @Test
    void testDelete() throws Exception {
        Customer customer = new Customer(
                1L,
                "Max",
                "Mustermann",
                "max@test.at",
                "Male",
                LocalDate.of(2005, 9, 18)
        );

        when(customerService.deleteById(1L)).thenReturn(Optional.of(customer));

        mockMvc.perform(delete("/api/custommer/delete/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));

        verify(customerService).deleteById(1L);
    }
}
