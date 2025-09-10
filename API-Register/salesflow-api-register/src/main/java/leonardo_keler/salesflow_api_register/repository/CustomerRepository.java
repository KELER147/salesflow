package leonardo_keler.salesflow_api_register.repository;

import leonardo_keler.salesflow_api_register.entity.Customer;
import leonardo_keler.salesflow_api_register.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Optional<Customer> findByCpf(String cpf);
}
