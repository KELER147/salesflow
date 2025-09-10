package leonardo_keler.salesflow_api_register.controller;

import jakarta.validation.Valid;
import leonardo_keler.salesflow_api_register.dto.auth.LoginRequestDTO;
import leonardo_keler.salesflow_api_register.dto.auth.LoginResponseDTO;
import leonardo_keler.salesflow_api_register.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api-register/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginRequestDTO data) {
        // 1. Cria um objeto de autenticação com o email e senha recebidos
        var authenticationToken = new UsernamePasswordAuthenticationToken(data.email(), data.password());

        // 2. O AuthenticationManager usa o UserDetailsService para validar o usuário e a senha
        var authentication = this.authenticationManager.authenticate(authenticationToken);

        // 3. Se a autenticação for bem-sucedida, pega os detalhes do usuário
        var userDetails = (UserDetails) authentication.getPrincipal();

        // 4. Usa o TokenService para gerar o token JWT
        var jwtToken = tokenService.generateToken(userDetails);

        // 5. Retorna o token na resposta
        return ResponseEntity.ok(new LoginResponseDTO(jwtToken));
    }
}