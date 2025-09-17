package leonardo_keler.salesflow_api_stock.service;

import leonardo_keler.salesflow_api_stock.dto.brand.BrandDTO;
import leonardo_keler.salesflow_api_stock.dto.category.CategoryDTO;
import leonardo_keler.salesflow_api_stock.dto.product.ProductRequestDTO;
import leonardo_keler.salesflow_api_stock.dto.product.ProductResponseDTO;
import leonardo_keler.salesflow_api_stock.dto.product.ProductUpdateDTO;
import leonardo_keler.salesflow_api_stock.entity.Brand;
import leonardo_keler.salesflow_api_stock.entity.Category;
import leonardo_keler.salesflow_api_stock.entity.Product;
import leonardo_keler.salesflow_api_stock.exception.ResourceNotFoundException;
import leonardo_keler.salesflow_api_stock.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final BrandService brandService;
    private final CategoryService categoryService;


    @Transactional
    public ProductResponseDTO create(ProductRequestDTO requestDTO) {
        Brand brand = brandService.findBrandById(requestDTO.brandId());
        Category category = categoryService.findCategoryById(requestDTO.categoryId());

        Product product = new Product();
        product.setName(requestDTO.name());
        product.setSku(requestDTO.sku());
        product.setBarcode(requestDTO.barcode());
        product.setDescription(requestDTO.description());
        product.setCostPrice(requestDTO.costPrice());
        product.setSalePrice(requestDTO.salePrice());
        product.setQuantity(requestDTO.quantity());
        product.setMinimumStock(requestDTO.minimumStock());
        product.setVolume(requestDTO.volume());
        product.setExpirationDate(requestDTO.expirationDate());
        product.setBrand(brand);
        product.setCategory(category);
        product.setActive(true);

        Product savedProduct = productRepository.save(product);
        return convertToResponseDTO(savedProduct);
    }


    @Transactional(readOnly = true)
    public List<ProductResponseDTO> findAll() {
        return productRepository.findAll()
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }


    @Transactional(readOnly = true)
    public ProductResponseDTO findById(Long id) {
        Product product = getProductById(id);
        return convertToResponseDTO(product);
    }


    @Transactional
    public ProductResponseDTO update(Long id, ProductUpdateDTO updateDTO) {
        Product productToUpdate = getProductById(id);

        if (updateDTO.name() != null) productToUpdate.setName(updateDTO.name());
        if (updateDTO.description() != null) productToUpdate.setDescription(updateDTO.description());
        if (updateDTO.costPrice() != null) productToUpdate.setCostPrice(updateDTO.costPrice());
        if (updateDTO.salePrice() != null) productToUpdate.setSalePrice(updateDTO.salePrice());
        if (updateDTO.quantity() != null) productToUpdate.setQuantity(updateDTO.quantity());
        if (updateDTO.minimumStock() != null) productToUpdate.setMinimumStock(updateDTO.minimumStock());
        if (updateDTO.volume() != null) productToUpdate.setVolume(updateDTO.volume());
        if (updateDTO.expirationDate() != null) productToUpdate.setExpirationDate(updateDTO.expirationDate());
        if (updateDTO.brandId() != null) {
            productToUpdate.setBrand(brandService.findBrandById(updateDTO.brandId()));
        }
        if (updateDTO.categoryId() != null) {
            productToUpdate.setCategory(categoryService.findCategoryById(updateDTO.categoryId()));
        }

        Product updatedProduct = productRepository.save(productToUpdate);
        return convertToResponseDTO(updatedProduct);
    }

    @Transactional
    public void delete(Long id) {
        Product productToDelete = getProductById(id);
        productToDelete.setActive(false);
        productRepository.save(productToDelete);
    }

    @Transactional(readOnly = true)
    public List<ProductResponseDTO> findProductsExpiringSoon(Integer days) {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(days);

        List<Product> products = productRepository.findByExpirationDateBetween(startDate, endDate);

        return products.stream()
                .map(this::convertToResponseDTO)
                .toList();
    }




    private Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com o ID: " + id));
    }

    private ProductResponseDTO convertToResponseDTO(Product product) {
        BrandDTO brandDTO = new BrandDTO(product.getBrand().getId(), product.getBrand().getName());
        CategoryDTO categoryDTO = new CategoryDTO(product.getCategory().getId(), product.getCategory().getName(), product.getCategory().getDescription());

        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getSku(),
                product.getBarcode(),
                product.getDescription(),
                product.getCostPrice(),
                product.getSalePrice(),
                product.getQuantity(),
                product.getMinimumStock(),
                product.getVolume(),
                product.getExpirationDate(),
                product.getCreatedAt(),
                product.getUpdatedAt(),
                brandDTO,
                categoryDTO,
                product.isActive()
        );
    }
}