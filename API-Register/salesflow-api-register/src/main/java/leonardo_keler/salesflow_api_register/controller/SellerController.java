package leonardo_keler.salesflow_api_register.controller;

import jakarta.validation.Valid;
import leonardo_keler.salesflow_api_register.dto.seller.SellerCreateDTO;
import leonardo_keler.salesflow_api_register.dto.seller.SellerPasswordUpdateDTO;
import leonardo_keler.salesflow_api_register.dto.seller.SellerResponseDTO;
import leonardo_keler.salesflow_api_register.dto.seller.SellerUpdateDTO;
import leonardo_keler.salesflow_api_register.service.SellerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api-register/sellers")
public class SellerController {

    private final SellerService sellerService;

    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }


    @PostMapping(value = "/create")
    public ResponseEntity<SellerResponseDTO> sellerResponseDTO(@RequestBody  @Valid SellerCreateDTO sellerCreateDTO) {
        SellerResponseDTO sellerResponseDTO = sellerService.createSeller(sellerCreateDTO);
        return ResponseEntity.ok().body(sellerResponseDTO);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SellerResponseDTO> updateSeller(@PathVariable Long id, @RequestBody @Valid SellerUpdateDTO dto) {
        SellerResponseDTO updatedSeller = sellerService.updateSeller(id, dto);
        return ResponseEntity.ok(updatedSeller);
    }

    @PatchMapping("/update/{id}/password")
    public ResponseEntity<Void> updatePassword(@PathVariable Long id, @RequestBody @Valid SellerPasswordUpdateDTO dto) {
        sellerService.updatePassword(id, dto);
        // Retorna 204 No Content, um status de sucesso que não precisa de corpo na resposta.
        return ResponseEntity.noContent().build();
    }
}
