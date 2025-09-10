package leonardo_keler.salesflow_api_register.service;

import leonardo_keler.salesflow_api_register.dto.SellerCreateDTO;
import leonardo_keler.salesflow_api_register.dto.SellerResponseDTO;
import leonardo_keler.salesflow_api_register.entity.Seller;
import leonardo_keler.salesflow_api_register.repository.SellerRepository;
import org.springframework.stereotype.Service;

@Service
public class SellerService {

    SellerRepository sellerRepository;
    public SellerService(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
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
        Seller seller = new Seller(
                dto.name(),
                dto.email(),
                dto.cpf(),
                dto.phone(),
                dto.password()
        );

        // Salva o Novo Vendedor
        sellerRepository.save(seller);

        // Cria um SellerResponseDTO
        return new SellerResponseDTO(
                seller.getId(),
                seller.getName(),
                seller.getEmail(),
                seller.getCpf()
        );
    }
}
