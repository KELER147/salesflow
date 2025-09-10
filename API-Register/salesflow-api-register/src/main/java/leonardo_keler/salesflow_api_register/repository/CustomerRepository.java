package leonardo_keler.salesflow_api_register.repository;

import leonardo_keler.salesflow_api_register.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Seller, Integer> {
}
