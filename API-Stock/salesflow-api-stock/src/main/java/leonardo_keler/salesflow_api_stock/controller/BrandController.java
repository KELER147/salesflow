package leonardo_keler.salesflow_api_stock.controller;

import jakarta.validation.Valid;
import leonardo_keler.salesflow_api_stock.dto.brand.BrandDTO;
import leonardo_keler.salesflow_api_stock.dto.brand.BrandRequestDTO;
import leonardo_keler.salesflow_api_stock.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "api-stock/brands") // <-- URL CORRIGIDA
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;

    @PostMapping("/create")
    public ResponseEntity<BrandDTO> create(@Valid @RequestBody BrandRequestDTO requestDTO) {
        BrandDTO createdBrand = brandService.create(requestDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(createdBrand.id()).toUri();
        return ResponseEntity.created(location).body(createdBrand);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<BrandDTO>> findAll() {
        return ResponseEntity.ok(brandService.findAll());
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<BrandDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(brandService.findById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<BrandDTO>> searchByName(@RequestParam String name) {
        return ResponseEntity.ok(brandService.searchByName(name));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BrandDTO> update(@PathVariable Long id, @Valid @RequestBody BrandRequestDTO requestDTO) {
        return ResponseEntity.ok(brandService.update(id, requestDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        brandService.delete(id);
        return ResponseEntity.noContent().build();
    }
}