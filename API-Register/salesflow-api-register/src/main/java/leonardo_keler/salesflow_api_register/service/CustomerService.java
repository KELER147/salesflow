package leonardo_keler.salesflow_api_register.service;

import leonardo_keler.salesflow_api_register.dto.customer.CustomerCreateDTO;
import leonardo_keler.salesflow_api_register.dto.customer.CustomerResponseDTO;
import leonardo_keler.salesflow_api_register.dto.shared.EnderecoDTO;
import leonardo_keler.salesflow_api_register.entity.Customer;
import leonardo_keler.salesflow_api_register.entity.Endereco;
import leonardo_keler.salesflow_api_register.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerResponseDTO createCustomer(CustomerCreateDTO dto) {
        customerRepository.findByCpf(dto.cpf())
                .ifPresent(c -> { throw new RuntimeException("This CPF is already registered."); });

        Endereco endereco = new Endereco(
                dto.enderecoDTO().streetName(),
                dto.enderecoDTO().neighborhood(),
                dto.enderecoDTO().complement(),
                dto.enderecoDTO().city(),
                dto.enderecoDTO().state(),
                dto.enderecoDTO().number()
        );

        Customer newCustomer = new Customer();
        newCustomer.setName(dto.name());
        newCustomer.setEmail(dto.email());
        newCustomer.setCpf(dto.cpf());
        newCustomer.setPhone(dto.phone());
        newCustomer.setEndereco(endereco);

        Customer savedCustomer = customerRepository.save(newCustomer);

        return toResponseDTO(savedCustomer);
    }

    public List<CustomerResponseDTO> findAll() {
        List<Customer> customers = customerRepository.findAll();
        if (customers.isEmpty()) {
            throw new IllegalStateException("No customers found.");
        }
        return customers.stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public CustomerResponseDTO findById(Long id) {
        Customer customer = findCustomerById(id);
        return toResponseDTO(customer);
    }



    private Customer findCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with ID: " + id));
    }

    private CustomerResponseDTO toResponseDTO(Customer customer) {
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
