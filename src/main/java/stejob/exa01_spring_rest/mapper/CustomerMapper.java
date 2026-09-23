package stejob.exa01_spring_rest.mapper;

import org.mapstruct.Mapper;
import stejob.exa01_spring_rest.dto.CustomerDto;
import stejob.exa01_spring_rest.entities.Customer;


@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerDto toDto(Customer customer);

    Customer toEntity(CustomerDto customerDto);
}
