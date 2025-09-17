package leonardo_keler.salesflow_api_stock.service;

import leonardo_keler.salesflow_api_stock.exception.DataIntegrityViolationException;
import leonardo_keler.salesflow_api_stock.repository.ProductRepository;
import org.springframework.transaction.annotation.Transactional;
import leonardo_keler.salesflow_api_stock.dto.brand.BrandDTO;
import leonardo_keler.salesflow_api_stock.dto.brand.BrandRequestDTO;
import leonardo_keler.salesflow_api_stock.entity.Brand;
import leonardo_keler.salesflow_api_stock.exception.ResourceNotFoundException;
import leonardo_keler.salesflow_api_stock.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;
    private final ProductRepository productRepository;


    @Transactional
    public BrandDTO create(BrandRequestDTO requestDTO) {
        Brand brand = new Brand();
        brand.setName(requestDTO.name());
        Brand savedBrand = brandRepository.save(brand);
        return convertToDTO(savedBrand);
    }

    @Transactional(readOnly = true)
    public List<BrandDTO> findAll() {
        return brandRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public BrandDTO update(Long id, BrandRequestDTO requestDTO) {
        Brand brand = findBrandById(id);
        brand.setName(requestDTO.name());
        Brand updatedBrand = brandRepository.save(brand);
        return convertToDTO(updatedBrand);
    }


    @Transactional
    public void delete(Long id) {
        // 1. Garante que a marca existe
        findBrandById(id);

        // 2. VERIFICA SE A MARCA ESTÁ EM USO
        if (productRepository.existsByBrandId(id)) {
            throw new DataIntegrityViolationException(
                    "Esta marca não pode ser excluída pois está em uso por um ou mais produtos."
            );
        }

        // 3. Se não estiver em uso, deleta
        brandRepository.deleteById(id);
    }
    public Brand findBrandById(Long id) {
        return brandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Marca não encontrada com o ID: " + id));
    }


    public BrandDTO findById(Long id) {
        return convertToDTO(findBrandById(id));
    }

    private BrandDTO convertToDTO(Brand brand) {
        return new BrandDTO(brand.getId(), brand.getName());
    }
}
