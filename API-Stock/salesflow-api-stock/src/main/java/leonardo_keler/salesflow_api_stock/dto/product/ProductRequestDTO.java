package leonardo_keler.salesflow_api_stock.dto.product;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ProductRequestDTO(
        @NotBlank(message = "O nome do produto não pode ser vazio.")
        String name,

        @NotBlank(message = "O SKU do produto não pode ser vazio.")
        String sku,

        @Pattern(regexp = "^[0-9]{8,13}$", message = "O código de barras deve conter entre 8 e 13 dígitos numéricos.")
        String barcode,

        String description,

        @NotNull(message = "O preço de custo é obrigatório.")
        @Positive(message = "O preço de custo deve ser um valor positivo.")
        BigDecimal costPrice,

        @NotNull(message = "O preço de venda é obrigatório.")
        @Positive(message = "O preço de venda deve ser um valor positivo.")
        BigDecimal salePrice,

        @NotNull(message = "A quantidade em estoque é obrigatória.")
        @Min(value = 0, message = "A quantidade não pode ser negativa.")
        Integer quantity,

        @NotNull(message = "O estoque mínimo é obrigatório.")
        @Min(value = 0, message = "O estoque mínimo não pode ser negativo.")
        Integer minimumStock,

        String volume,
        LocalDate expirationDate,

        @NotNull(message = "O ID da marca é obrigatório.")
        Long brandId, // Apenas o ID da marca, simples e direto

        @NotNull(message = "O ID da categoria é obrigatório.")
        Long categoryId // Apenas o ID da categoria
) { }
