package leonardo_keler.salesflow_api_register.dto;

public record CustomerCreateDTO(
        String name,
        String email,
        String cpf,
        String phone,
        EnderecoDTO enderecoDTO
) {}
