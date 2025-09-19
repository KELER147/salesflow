package leonardo_keler.salesflow_api_caixa.dto.sale;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import leonardo_keler.salesflow_api_caixa.dto.saleitem.SaleItemRequestDTO;
import leonardo_keler.salesflow_api_caixa.entity.enums.PaymentMethod;

import java.util.List;

public record SaleRequestDTO(
        @NotNull(message = "Payment method cannot be null")
        PaymentMethod paymentMethod,

        @NotNull(message = "Seller ID cannot be null")
        Long sellerId,

        Long customerId,

        @NotEmpty(message = "Sale must have at least one item")
        @Valid
        List<SaleItemRequestDTO> items
) {}