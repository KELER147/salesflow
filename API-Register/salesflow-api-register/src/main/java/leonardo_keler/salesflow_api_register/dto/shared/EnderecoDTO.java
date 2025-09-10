package leonardo_keler.salesflow_api_register.dto.shared;

public record EnderecoDTO(
        String streetName,
        String neighborhood,
        String complement,
        String city,
        String state,
        Integer number
) {}
