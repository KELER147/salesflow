package leonardo_keler.salesflow_api_caixa.repository;

import leonardo_keler.salesflow_api_caixa.entity.CashRegisterSession;
import leonardo_keler.salesflow_api_caixa.entity.enums.SessionStatus;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

@Repository
public interface CashRegisterSessionRepository extends JpaRepository<CashRegisterSession, Long> {

    Optional<CashRegisterSession> findByStatus(SessionStatus status);

}