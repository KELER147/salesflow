package leonardo_keler.salesflow_api_caixa.integration.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record StockDebitDTO(
        @NotNull Long productId,
        @NotNull @Positive Integer quantity
) {}