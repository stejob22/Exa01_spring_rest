package stejob.exa01_spring_rest.pojos.mapper;

import org.mapstruct.Mapper;
import stejob.exa01_spring_rest.pojos.dto.CustomerDto;
import stejob.exa01_spring_rest.pojos.entities.Customer;


@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerDto toDto(Customer customer);

    Customer toEntity(CustomerDto customerDto);
}
