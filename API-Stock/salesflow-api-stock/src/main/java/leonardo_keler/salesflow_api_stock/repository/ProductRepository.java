package leonardo_keler.salesflow_api_stock.repository;

import leonardo_keler.salesflow_api_stock.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByExpirationDateBetween(LocalDate startDate, LocalDate endDate);

    List<Product> findByNameContainingIgnoreCase(String name);

    boolean existsByBrandId(Long brandId);

    boolean existsByCategoryId(Long categoryId);

}