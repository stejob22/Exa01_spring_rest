package stejob.exa01_spring_rest.pojos.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record CustomerDto(
        Long id,
        String firstname,
        String lastname,
        @JsonFormat(pattern = "dd-MM-yyyy")
        LocalDate birthday
) {
    public CustomerDto {
        if (birthday.isAfter(LocalDate.now().minusYears(12))) {
            throw new RuntimeException(("Too young"));
        }
    }

    public static void main(String[] args) {
        try {
            CustomerDto customerDto = new CustomerDto(1L, "asa", "asa", LocalDate.now());
            System.out.println(customerDto);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

    }
}