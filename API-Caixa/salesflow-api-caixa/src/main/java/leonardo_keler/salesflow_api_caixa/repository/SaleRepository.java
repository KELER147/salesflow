package leonardo_keler.salesflow_api_caixa.repository;

import leonardo_keler.salesflow_api_caixa.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {
}
