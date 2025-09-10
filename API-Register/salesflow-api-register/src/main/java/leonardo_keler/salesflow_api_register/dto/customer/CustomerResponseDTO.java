package leonardo_keler.salesflow_api_register.dto.customer;

import leonardo_keler.salesflow_api_register.dto.shared.EnderecoDTO;

public record CustomerResponseDTO(
        Long id,
        String name,
        String email,
        String cpf,
        String phone,
        EnderecoDTO endereco
) {}
