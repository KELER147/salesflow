package leonardo_keler.salesflow_api_stock.service;

import leonardo_keler.salesflow_api_stock.dto.category.CategoryDTO;
import leonardo_keler.salesflow_api_stock.dto.category.CategoryRequestDTO;
import leonardo_keler.salesflow_api_stock.entity.Category;
import leonardo_keler.salesflow_api_stock.exception.DataIntegrityViolationException;
import leonardo_keler.salesflow_api_stock.exception.ResourceNotFoundException;
import leonardo_keler.salesflow_api_stock.repository.CategoryRepository;
import leonardo_keler.salesflow_api_stock.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Transactional
    public CategoryDTO create(CategoryRequestDTO requestDTO) {
        Category category = new Category();
        category.setName(requestDTO.name());
        category.setDescription(requestDTO.description());
        Category savedCategory = categoryRepository.save(category);
        return convertToDTO(savedCategory);
    }


    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll() {
        return categoryRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public CategoryDTO update(Long id, CategoryRequestDTO requestDTO) {
        Category category = findCategoryById(id);
        category.setName(requestDTO.name());
        category.setDescription(requestDTO.description());
        Category updatedCategory = categoryRepository.save(category);
        return convertToDTO(updatedCategory);
    }


    @Transactional
    public void delete(Long id) {
        // 1. Garante que a categoria existe
        findCategoryById(id);

        // 2. VERIFICA SE A CATEGORIA ESTÁ EM USO
        if (productRepository.existsByCategoryId(id)) {
            throw new DataIntegrityViolationException(
                    "Esta categoria não pode ser excluída pois está em uso por um ou mais produtos."
            );
        }

        // 3. Se não estiver em uso, deleta
        categoryRepository.deleteById(id);
    }


    public Category findCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada com o ID: " + id));
    }

    public CategoryDTO findById(Long id) {
        return convertToDTO(findCategoryById(id));
    }

    private CategoryDTO convertToDTO(Category category) {
        return new CategoryDTO(category.getId(), category.getName(), category.getDescription());
    }
}