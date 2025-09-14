package leonardo_keler.salesflow_api_register.service;

import jakarta.persistence.EntityNotFoundException;
import leonardo_keler.salesflow_api_register.dto.seller.*;
import leonardo_keler.salesflow_api_register.entity.Seller;
import leonardo_keler.salesflow_api_register.repository.SellerRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerService {

    private final PasswordEncoder passwordEncoder;
    private final SellerRepository sellerRepository;

    public SellerService(SellerRepository sellerRepository, PasswordEncoder passwordEncoder) {
        this.sellerRepository = sellerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public SellerResponseDTO createSeller(SellerCreateDTO dto) {
        validateUniqueCpfAndEmail(dto.cpf(), dto.email());

        Seller newSeller = new Seller();
        newSeller.setName(dto.name());
        newSeller.setEmail(dto.email());
        newSeller.setCpf(dto.cpf());
        newSeller.setPassword(passwordEncoder.encode(dto.password()));
        newSeller.setPhone(dto.phone());

        sellerRepository.save(newSeller);

        return toResponseDTO(newSeller);
    }

    public SellerResponseDTO updateSeller(Long id, SellerUpdateDTO dto) {
        Seller sellerToUpdate = findSellerById(id);

        if (dto.name() != null && !dto.name().isBlank()) {
            sellerToUpdate.setName(dto.name());
        }
        if (dto.phone() != null && !dto.phone().isBlank()) {
            sellerToUpdate.setPhone(dto.phone());
        }

        return toResponseDTO(sellerRepository.save(sellerToUpdate));
    }

    public void updatePassword(Long id, SellerPasswordUpdateDTO dto) {
        Seller seller = findSellerById(id);

        if (!passwordEncoder.matches(dto.oldPassword(), seller.getPassword())) {
            throw new RuntimeException("Incorrect old password.");
        }

        seller.setPassword(passwordEncoder.encode(dto.newPassword()));
        sellerRepository.save(seller);
    }

    public List<SellerResponseDTO> findAll() {
        List<Seller> sellers = sellerRepository.findAll();
        if (sellers.isEmpty()) {
            throw new IllegalStateException("No sellers found.");
        }
        return sellers.stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public SellerResponseDTO findById(Long id) {
        return toResponseDTO(findSellerById(id));
    }



    private Seller findSellerById(Long id) {
        return sellerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Seller not found with ID: " + id));
    }

    private void validateUniqueCpfAndEmail(String cpf, String email) {
        if (sellerRepository.findByCpf(cpf).isPresent()) {
            throw new RuntimeException("This CPF is already registered.");
        }
        if (sellerRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("This email is already registered.");
        }
    }

    private SellerResponseDTO toResponseDTO(Seller seller) {
        return new SellerResponseDTO(
                seller.getId(),
                seller.getName(),
                seller.getEmail(),
                seller.getCpf()
        );
    }
}
