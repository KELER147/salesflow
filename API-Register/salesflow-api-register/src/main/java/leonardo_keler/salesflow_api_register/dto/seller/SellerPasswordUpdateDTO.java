package leonardo_keler.salesflow_api_register.dto.seller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SellerPasswordUpdateDTO(
        @NotBlank
        String oldPassword,

        @NotBlank
        @Size(min = 8, message = "A nova senha deve ter pelo menos 8 caracteres")
        String newPassword
) {}
