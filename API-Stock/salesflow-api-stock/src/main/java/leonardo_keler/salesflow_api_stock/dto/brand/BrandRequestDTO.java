package leonardo_keler.salesflow_api_stock.dto.brand;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record BrandRequestDTO(
        @NotBlank(message = "O nome da marca é obrigatório.")
        @Size(max = 50, message = "O nome da marca deve ter no máximo 50 caracteres.")
        String name
) {}
