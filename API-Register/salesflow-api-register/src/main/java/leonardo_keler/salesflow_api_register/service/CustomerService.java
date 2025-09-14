package leonardo_keler.salesflow_api_register.service;

import leonardo_keler.salesflow_api_register.dto.customer.CustomerCreateDTO;
import leonardo_keler.salesflow_api_register.dto.customer.CustomerResponseDTO;
import leonardo_keler.salesflow_api_register.dto.seller.SellerResponseDTO;
import leonardo_keler.salesflow_api_register.dto.shared.EnderecoDTO;
import leonardo_keler.salesflow_api_register.entity.Customer;
import leonardo_keler.salesflow_api_register.entity.Endereco;
import leonardo_keler.salesflow_api_register.entity.Seller;
import leonardo_keler.salesflow_api_register.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.LongFunction;

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

    public List<CustomerResponseDTO> findAll() {
        if (customerRepository.findAll().isEmpty()) {
            throw new IllegalStateException("Nenhum Customer encontrado");
        }
        List<Customer> listCustomer = customerRepository.findAll();
        List<CustomerResponseDTO> listDTO = new ArrayList<>();


        for  (Customer customer : listCustomer) {

          EnderecoDTO enderecoDTO = new EnderecoDTO(
                  customer.getEndereco().getStreetName(),
                  customer.getEndereco().getNeighborhood(),
                  customer.getEndereco().getComplement(),
                  customer.getEndereco().getCity(),
                  customer.getEndereco().getState(),
                  customer.getEndereco().getNumber()
          );

            CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO(
                    customer.getId(),
                    customer.getName(),
                    customer.getEmail(),
                    customer.getCpf(),
                    customer.getPhone(),
                    enderecoDTO
            );
            listDTO.add(customerResponseDTO);
        }
        return listDTO;
    }

    public CustomerResponseDTO findById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Seller com o ID: " + id + " não encontrado"));

        EnderecoDTO enderecoDTO = new EnderecoDTO(
                customer.getEndereco().getStreetName(),
                customer.getEndereco().getNeighborhood(),
                customer.getEndereco().getComplement(),
                customer.getEndereco().getCity(),
                customer.getEndereco().getState(),
                customer.getEndereco().getNumber()
        );

        return new CustomerResponseDTO(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getCpf(),
                customer.getPhone(),
                enderecoDTO
        );
    }
}
