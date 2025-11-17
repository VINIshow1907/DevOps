package br.com.lojaads.lojaads.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.lojaads.lojaads.entity.ItemPedido;
import br.com.lojaads.lojaads.entity.Pedido;
import br.com.lojaads.lojaads.entity.Produto;
import br.com.lojaads.lojaads.entity.Usuario;
import br.com.lojaads.lojaads.repository.ItemPedidoRepository;
import br.com.lojaads.lojaads.repository.PedidoRepository;
import br.com.lojaads.lojaads.repository.ProdutoRepository;
import jakarta.transaction.Transactional;

@Service
public class CarrinhoService {
    
    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;
    private final ItemPedidoRepository itemPedidoRepository;
 
    public CarrinhoService(PedidoRepository pedidoRepository,
                           ProdutoRepository produtoRepository,
                           ItemPedidoRepository itemPedidoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.produtoRepository = produtoRepository;
        this.itemPedidoRepository = itemPedidoRepository;
    }
 
@Transactional
public void adicionarProduto(Usuario usuario, Long idProduto) {
    Produto produto = produtoRepository.findById(idProduto)
            .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
 
    // Busca ou cria o carrinho do usuário
    Pedido pedido = pedidoRepository.findByUsuarioAndFinalizadoFalse(usuario)
            .orElseGet(() -> {
                Pedido novo = new Pedido();
                novo.setUsuario(usuario);
                novo.setFinalizado(false);
                novo.setItens(new ArrayList<>());
                novo.setTotal(0.0);
                return pedidoRepository.save(novo);
            });
 
    // Verifica se o produto já está no carrinho
    Optional<ItemPedido> existente = pedido.getItens().stream()
            .filter(i -> i.getProduto().getIdProduto().equals(produto.getIdProduto()))
            .findFirst();
 
    if (existente.isPresent()) {
        ItemPedido item = existente.get();
        item.setQuantidade(item.getQuantidade() + 1);
        item.setSubtotal(item.getQuantidade() * produto.getPreco());
        itemPedidoRepository.save(item);
    } else {
        ItemPedido item = new ItemPedido();
        item.setPedido(pedido);
        item.setProduto(produto);
        item.setQuantidade(1);
        item.setSubtotal(produto.getPreco());
        itemPedidoRepository.save(item);
        pedido.getItens().add(item);
    }
 
    atualizarTotal(pedido);
}
 
 
    public Pedido obterCarrinho(Usuario usuario) {
        return pedidoRepository.findByUsuarioAndItensIsNotEmpty(usuario).orElse(null);
    }                           
 
public void removerItem(Usuario usuario, Long idItem) {
    ItemPedido item = itemPedidoRepository.findById(idItem)
        .orElseThrow(() -> new RuntimeException("Item não encontrado"));
    if (!item.getPedido().getUsuario().equals(usuario)) {
        throw new RuntimeException("O item não pertence ao usuário logado");
    }
   
    Pedido pedido = item.getPedido();
    pedido.getItens().remove(item);  // Remove da lista para sincronizar
    itemPedidoRepository.delete(item);  // Remove do banco
    atualizarTotal(pedido);  // Atualiza o total e salva o pedido
}
 
    private void atualizarTotal(Pedido pedido) {
        double total = pedido.getItens().stream()
                .mapToDouble(ItemPedido::getSubtotal)
                .sum();
        pedido.setTotal(total);
        pedidoRepository.save(pedido);
    }
}
