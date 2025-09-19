package leonardo_keler.salesflow_api_caixa.dto.cashregister;

import leonardo_keler.salesflow_api_caixa.entity.enums.SessionStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CashRegisterResponseDTO(
        Long id,
        LocalDateTime openingTime,
        LocalDateTime closingTime,
        BigDecimal initialAmount,
        BigDecimal finalCalculatedAmount,
        BigDecimal finalReportedAmount,
        BigDecimal difference,
        SessionStatus status,
        Long operatorId
) {}