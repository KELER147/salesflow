package leonardo_keler.salesflow_api_caixa.dto.cashregister;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record CashRegisterOpenDTO(
        @NotNull(message = "Initial amount cannot be null")
        @PositiveOrZero(message = "Initial amount must be zero or greater")
        BigDecimal initialAmount,

        @NotNull(message = "Operator ID cannot be null")
        Long operatorId
) {}