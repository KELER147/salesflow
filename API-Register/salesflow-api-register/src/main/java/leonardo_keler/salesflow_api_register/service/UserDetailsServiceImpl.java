package leonardo_keler.salesflow_api_register.service;

import leonardo_keler.salesflow_api_register.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final SellerRepository sellerRepository;

    @Autowired
    public UserDetailsServiceImpl(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // O "username" = email
        var seller = sellerRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Vendedor não encontrado com o email: " + username));

        // Se encontrou o vendedor, retorna um objeto ‘User’ do Spring Security
        // com o email, a senha (criptografada) e uma lista de permissões
        return new org.springframework.security.core.userdetails.User(
                seller.getEmail(),
                seller.getPassword(),
                new ArrayList<>()
        );
    }
}