package leonardo_keler.salesflow_api_register.config;// Em SecurityConfig.java

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                // Desabilita a proteção CSRF
                .csrf(csrf -> csrf.disable())

                // Define a política de criação de sessão como STATELESS (sem estado), essencial para JWT
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // Define as regras de autorização para as requisições HTTP
                .authorizeHttpRequests(authorize -> authorize

                        // Permite que TODAS as requisições para URLs que começam com "/api/cadastro/" sejam públicas
                        .requestMatchers("/api-register/**").permitAll()

                        // Para qualquer outra requisição, exige autenticação
                        .anyRequest().authenticated()
                )
                .build();
    }
}