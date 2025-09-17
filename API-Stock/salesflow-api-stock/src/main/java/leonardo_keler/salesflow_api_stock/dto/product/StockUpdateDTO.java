package leonardo_keler.salesflow_api_stock.dto.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


public record StockUpdateDTO(
        @NotNull(message = "A quantidade é obrigatória.")
        @Positive(message = "A quantidade a ser adicionada deve ser um número positivo.")
        Integer quantity
) {}