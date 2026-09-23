package stejob.exa01_spring_rest.pojos.mapper;

import org.mapstruct.Mapper;
import stejob.exa01_spring_rest.pojos.dto.CustomerDto;
import stejob.exa01_spring_rest.pojos.entities.Customer;

import java.util.List;


@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerDto toDto(Customer customer);

    List<CustomerDto> toDtoList(List<Customer> customerList);

    Customer toEntity(CustomerDto customerDto);
    List<Customer> toEntityList(List<CustomerDto> customerDtoList);
}
