package leonardo_keler.salesflow_api_register.service;

import leonardo_keler.salesflow_api_register.dto.CustomerCreateDTO;
import leonardo_keler.salesflow_api_register.dto.CustomerResponseDTO;
import leonardo_keler.salesflow_api_register.entity.Customer;
import leonardo_keler.salesflow_api_register.entity.Endereco;
import leonardo_keler.salesflow_api_register.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    CustomerRepository customerRepository;
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public CustomerResponseDTO createCustomer(CustomerCreateDTO dto) {
        // Verifica o CPF
        customerRepository.findByCpf(dto.cpf()).ifPresent(Customer -> {throw new RuntimeException("this CPF is already registered");});

        // Cria o Endereço
        Endereco endereco = new Endereco(
                dto.enderecoDTO().streetName(),
                dto.enderecoDTO().neighborhood(),
                dto.enderecoDTO().complement(),
                dto.enderecoDTO().city(),
                dto.enderecoDTO().state(),
                dto.enderecoDTO().number()
        );

        // Cria o novo Cliente
        Customer newCustomer = new Customer();
        newCustomer.setName(dto.name());
        newCustomer.setEmail(dto.email());
        newCustomer.setCpf(dto.cpf());
        newCustomer.setPhone(dto.phone());
        newCustomer.setEndereco(endereco);

        // Sava o novo Cliente no Banco de dados
        Customer savedCustomer = customerRepository.save(newCustomer);

        // Retorna Um Cliente DTO
        return new CustomerResponseDTO(
                savedCustomer.getId(),
                savedCustomer.getName(),
                savedCustomer.getEmail(),
                savedCustomer.getCpf(),
                savedCustomer.getPhone(),
                dto.enderecoDTO()
        );
    }
}
