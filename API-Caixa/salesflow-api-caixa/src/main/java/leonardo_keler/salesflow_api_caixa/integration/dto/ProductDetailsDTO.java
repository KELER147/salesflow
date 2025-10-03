package leonardo_keler.salesflow_api_caixa.integration.dto;

import java.math.BigDecimal;

public record ProductDetailsDTO(
        Long id,
        BigDecimal price,
        Integer quantityInStock
) {}