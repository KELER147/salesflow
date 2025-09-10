package leonardo_keler.salesflow_api_register.controller;

import jakarta.validation.Valid;
import leonardo_keler.salesflow_api_register.dto.CustomerCreateDTO;
import leonardo_keler.salesflow_api_register.dto.CustomerResponseDTO;
import leonardo_keler.salesflow_api_register.entity.Customer;
import leonardo_keler.salesflow_api_register.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api-register/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<CustomerResponseDTO> createCustomer(@RequestBody @Valid CustomerCreateDTO customerCreateDTO) {
        CustomerResponseDTO customerResponseDTO = customerService.createCustomer(customerCreateDTO);
        return ResponseEntity.ok().body(customerResponseDTO);
    }

}
