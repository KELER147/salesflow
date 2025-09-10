package leonardo_keler.salesflow_api_register.dto;

public record SellerCreateDTO(
        String name,
        String email,
        String cpf,
        String phone,
        String password
) {}
