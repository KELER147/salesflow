package leonardo_keler.salesflow_api_register.dto;

public record CustomerResponseDTO(
        Long id,
        String name,
        String email,
        String cpf,
        String phone,
        EnderecoDTO endereco
) {}
