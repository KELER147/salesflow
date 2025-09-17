package leonardo_keler.salesflow_api_stock.repository;

import leonardo_keler.salesflow_api_stock.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByNameContainingIgnoreCase(String name);

}
