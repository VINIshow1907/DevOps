package br.com.lojaads.lojaads.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.lojaads.lojaads.entity.Pedido;
import br.com.lojaads.lojaads.entity.Usuario;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{

    List<Pedido> findByUsuario(Usuario usuario);

    @Query("SELECT p FROM Pedido p WHERE p.usuario = :usuario AND SIZE(P.itens) > 0")

    Optional<Pedido> findByUsuarioAndItensIsNotEmpty(@Param("usuario") Usuario usuario);

    Optional<Pedido> findByUsuarioAndFinalizadoFalse(Usuario usuario);


}
