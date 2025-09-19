package leonardo_keler.salesflow_api_caixa.entity;

import jakarta.persistence.*;
import leonardo_keler.salesflow_api_caixa.entity.enums.SessionStatus;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "cash_register_sessions")
@Data
public class CashRegisterSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(name = "opening_time", nullable = false, updatable = false)
    private LocalDateTime openingTime;

    @Column(name = "closing_time")
    private LocalDateTime closingTime;

    @Column(name = "initial_amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal initialAmount;

    @Column(name = "final_calculated_amount", precision = 10, scale = 2)
    private BigDecimal finalCalculatedAmount;

    @Column(name = "final_reported_amount", precision = 10, scale = 2)
    private BigDecimal finalReportedAmount;


    @Column(precision = 10, scale = 2)
    private BigDecimal difference;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SessionStatus status;

    @Column(name = "operator_id", nullable = false)
    private Long operatorId;
}