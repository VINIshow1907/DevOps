package br.com.lojaads.lojaads.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
 
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
 
import br.com.lojaads.lojaads.service.UsuarioDetailsService;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

@Configuration
public class SecurityConfig {
 
    private final UsuarioDetailsService usuarioDetailsService;
 
    public SecurityConfig(UsuarioDetailsService usuarioDetailsService) {
        this.usuarioDetailsService = usuarioDetailsService;
    }
 
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                // libera acesso às rotas públicas (inclusive redefinição de senha)
                .requestMatchers(
                    "/login",
                    "/css/**",
                    "/img/**",
                    "/js/**",
                    "/solicitar-recuperacao",
                    "/redefinir-senha",
                    "/enviar-email-recuperacao" // caso tenha uma rota POST para envio do e-mail
                ).permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/home", true)
                .permitAll()
            )
            .logout(logout -> logout.permitAll())
 
            // 🚨 Desativa proteção CSRF (apenas durante testes)
            // Isso evita erro 403 ao enviar formulários POST sem token CSRF.
            .csrf(csrf -> csrf.disable());
 
        return http.build();
    }
 
    // ✅ Codificador de senhas (usado no login e no cadastro)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
 
    // ✅ Configura o provedor de autenticação usando o seu serviço de usuários
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(usuarioDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
}

