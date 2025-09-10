package leonardo_keler.salesflow_api_register.dto.seller;

public record SellerResponseDTO(
        Long id,
        String name,
        String email,
        String cpf
) {}
