package leonardo_keler.salesflow_api_stock.dto.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoryRequestDTO(
        @NotBlank(message = "O nome da categoria é obrigatório.")
        @Size(max = 50, message = "O nome da categoria deve ter no máximo 50 caracteres.")
        String name,

        @Size(max = 200, message = "A descrição deve ter no máximo 200 caracteres.")
        String description
) {}
