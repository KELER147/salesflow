package leonardo_keler.salesflow_api_stock.dto.product;

import leonardo_keler.salesflow_api_stock.dto.brand.BrandDTO;
import leonardo_keler.salesflow_api_stock.dto.category.CategoryDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record ProductResponseDTO(
        Long id,
        String name,
        String sku,
        String barcode,
        String description,
        BigDecimal costPrice,
        BigDecimal salePrice,
        Integer quantity,
        Integer minimumStock,
        String volume,
        LocalDate expirationDate,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        BrandDTO brand, // Objeto aninhado com os dados da marca
        CategoryDTO category, // Objeto aninhado com os dados da categoria
        boolean active
) {}
