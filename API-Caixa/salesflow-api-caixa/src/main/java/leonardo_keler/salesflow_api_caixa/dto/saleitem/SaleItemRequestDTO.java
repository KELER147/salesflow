package leonardo_keler.salesflow_api_caixa.dto.saleitem;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record SaleItemRequestDTO(
        @NotNull(message = "Product ID cannot be null")
        Long productId,

        @NotNull(message = "Quantity cannot be null")
        @Positive(message = "Quantity must be greater than zero")
        Integer quantity
) {}