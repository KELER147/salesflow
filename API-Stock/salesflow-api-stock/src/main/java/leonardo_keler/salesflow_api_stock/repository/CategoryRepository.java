package leonardo_keler.salesflow_api_stock.repository;

import leonardo_keler.salesflow_api_stock.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
