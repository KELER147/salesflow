package leonardo_keler.salesflow_api_stock.dto.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ProductUpdateDTO(
        @Size(min = 1, message = "O nome do produto não pode ser vazio.")
        String name,

        String description,

        @Positive(message = "O preço de custo deve ser um valor positivo.")
        BigDecimal costPrice,

        @Positive(message = "O preço de venda deve ser um valor positivo.")
        BigDecimal salePrice,

        @Min(value = 0, message = "A quantidade não pode ser negativa.")
        Integer quantity,

        @Min(value = 0, message = "O estoque mínimo não pode ser negativo.")
        Integer minimumStock,

        String volume,

        LocalDate expirationDate,

        Long brandId,

        Long categoryId
) {}
