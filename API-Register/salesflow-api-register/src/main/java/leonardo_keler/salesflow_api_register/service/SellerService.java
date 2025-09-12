package leonardo_keler.salesflow_api_register.service;

import jakarta.persistence.EntityNotFoundException;
import leonardo_keler.salesflow_api_register.dto.seller.SellerCreateDTO;
import leonardo_keler.salesflow_api_register.dto.seller.SellerPasswordUpdateDTO;
import leonardo_keler.salesflow_api_register.dto.seller.SellerResponseDTO;
import leonardo_keler.salesflow_api_register.dto.seller.SellerUpdateDTO;
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

    public SellerResponseDTO updateSeller(Long id, SellerUpdateDTO dto) {
        Seller sellerToUpdate = sellerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vendedor não encontrado com o ID: " + id));
        if (dto.name() != null && !dto.name().isBlank()) {
            sellerToUpdate.setName(dto.name());
        }
        if (dto.phone() != null && !dto.phone().isBlank()) {
            sellerToUpdate.setPhone(dto.phone());
        }

        Seller updatedSeller = sellerRepository.save(sellerToUpdate);
        return new SellerResponseDTO(
                updatedSeller.getId(),
                updatedSeller.getName(),
                updatedSeller.getEmail(),
                updatedSeller.getCpf()
        );
    }

    public void updatePassword(Long id, SellerPasswordUpdateDTO dto) {
        Seller seller = sellerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vendedor não encontrado com o ID: " + id));

        if (!passwordEncoder.matches(dto.oldPassword(), seller.getPassword())) {
            throw new RuntimeException("Senha antiga incorreta.");
        }

        String newPasswordEncoded = passwordEncoder.encode(dto.newPassword());
        seller.setPassword(newPasswordEncoded);
        sellerRepository.save(seller);
    }

}
