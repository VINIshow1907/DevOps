package br.com.lojaads.lojaads.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lojaads.lojaads.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByLogin(String login);

    
}
