package leonardo_keler.salesflow_api_register.repository;

import leonardo_keler.salesflow_api_register.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SellerRepository extends JpaRepository<Seller, Integer> {

    Optional<Seller> findByCpf(String cpf);
    Optional<Seller> findByEmail(String email);
    Optional<Seller> findById(Long id);
}
