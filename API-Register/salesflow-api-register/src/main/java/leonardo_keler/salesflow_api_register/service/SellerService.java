package leonardo_keler.salesflow_api_register.service;

import leonardo_keler.salesflow_api_register.dto.SellerCreateDTO;
import leonardo_keler.salesflow_api_register.dto.SellerResponseDTO;
import leonardo_keler.salesflow_api_register.entity.Seller;
import leonardo_keler.salesflow_api_register.repository.SellerRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SellerService {

    private final PasswordEncoder passwordEncoder;
    SellerRepository sellerRepository;

    public SellerService(SellerRepository sellerRepository, PasswordEncoder passwordEncoder) {
        this.sellerRepository = sellerRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public SellerResponseDTO createSeller(SellerCreateDTO dto) {
        // Verificação se já existe cpf e email no banco de dados
        if (sellerRepository.findByCpf(dto.cpf()).isPresent()) {
            throw new RuntimeException("this CPF is already registered");
        }
        if (sellerRepository.findByEmail(dto.email()).isPresent()) {
            throw new RuntimeException("this Email is already registered");
        }

        // Cria o novo Vendedor
        Seller newSeller = new Seller();
        newSeller.setName(dto.name());
        newSeller.setEmail(dto.email());
        newSeller.setCpf(dto.cpf());
        newSeller.setPassword(passwordEncoder.encode(dto.password()));
        newSeller.setPhone(dto.phone());

        // Salva o Novo Vendedor
        sellerRepository.save(newSeller);

        // Cria um SellerResponseDTO
        return new SellerResponseDTO(
                newSeller.getId(),
                newSeller.getName(),
                newSeller.getEmail(),
                newSeller.getCpf()
        );
    }
}
