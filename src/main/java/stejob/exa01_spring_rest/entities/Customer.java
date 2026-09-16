package stejob.exa01_spring_rest.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jdk.jfr.Enabled;
import lombok.*;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {
    @Id
    @EqualsAndHashCode.Exclude
    private long id;
    private String firstname;
    private String lastname;
    private String email;
    private String gender;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthday;

}
