package leonardo_keler.salesflow_api_caixa.dto.cashregister;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record CashRegisterCloseDTO(
        @NotNull(message = "Reported amount cannot be null")
        @PositiveOrZero(message = "Reported amount must be zero or greater")
        BigDecimal reportedAmount
) {}