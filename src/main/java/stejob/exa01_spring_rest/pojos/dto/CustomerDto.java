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
}
