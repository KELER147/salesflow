package leonardo_keler.salesflow_api_stock.controller;

import jakarta.validation.Valid;
import leonardo_keler.salesflow_api_stock.dto.product.ProductRequestDTO;
import leonardo_keler.salesflow_api_stock.dto.product.ProductResponseDTO;
import leonardo_keler.salesflow_api_stock.dto.product.ProductUpdateDTO;
import leonardo_keler.salesflow_api_stock.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "api-stock/products") // <-- URL CORRIGIDA
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<ProductResponseDTO> create(@Valid @RequestBody ProductRequestDTO requestDTO) {
        ProductResponseDTO createdProduct = productService.create(requestDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(createdProduct.id()).toUri();
        return ResponseEntity.created(location).body(createdProduct);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<ProductResponseDTO>> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<ProductResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductResponseDTO>> searchByName(@RequestParam String name) {
        return ResponseEntity.ok(productService.searchByName(name));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductResponseDTO> update(@PathVariable Long id, @Valid @RequestBody ProductUpdateDTO updateDTO) {
        return ResponseEntity.ok(productService.update(id, updateDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/expiring-soon") // /expiring-soon?days=40
    public ResponseEntity<List<ProductResponseDTO>> findExpiringSoon(@RequestParam(defaultValue = "30") Integer days) {
        return ResponseEntity.ok(productService.findProductsExpiringSoon(days));
    }
}