package leonardo_keler.salesflow_api_caixa.dto.sale;

import leonardo_keler.salesflow_api_caixa.dto.saleitem.SaleItemResponseDTO;
import leonardo_keler.salesflow_api_caixa.entity.enums.PaymentMethod;
import leonardo_keler.salesflow_api_caixa.entity.enums.SaleStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record SaleResponseDTO(
        Long id,
        LocalDateTime saleDate,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        SaleStatus status,
        Long sellerId,
        Long customerId,
        List<SaleItemResponseDTO> items
) {}