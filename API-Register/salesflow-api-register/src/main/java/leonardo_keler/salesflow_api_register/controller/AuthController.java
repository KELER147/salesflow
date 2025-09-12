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
        var authenticationToken = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var authentication = this.authenticationManager.authenticate(authenticationToken);
        var userDetails = (UserDetails) authentication.getPrincipal();
        var jwtToken = tokenService.generateToken(userDetails);
        return ResponseEntity.ok(new LoginResponseDTO(jwtToken));
    }
}