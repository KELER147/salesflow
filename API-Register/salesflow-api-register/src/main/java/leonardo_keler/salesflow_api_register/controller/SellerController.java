package leonardo_keler.salesflow_api_register.controller;

import jakarta.validation.Valid;
import leonardo_keler.salesflow_api_register.dto.seller.SellerCreateDTO;
import leonardo_keler.salesflow_api_register.dto.seller.SellerResponseDTO;
import leonardo_keler.salesflow_api_register.service.SellerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
