package leonardo_keler.salesflow_api_caixa.dto.saleitem;

import java.math.BigDecimal;

public record SaleItemResponseDTO(
        Long productId,
        Integer quantity,
        BigDecimal unitPrice,
        BigDecimal subtotal
) {}