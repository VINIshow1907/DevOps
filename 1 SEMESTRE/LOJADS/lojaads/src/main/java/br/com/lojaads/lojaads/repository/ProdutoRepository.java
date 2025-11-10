package br.com.lojaads.lojaads.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lojaads.lojaads.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    
}
