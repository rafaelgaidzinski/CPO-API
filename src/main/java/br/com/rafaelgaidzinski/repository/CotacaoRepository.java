package br.com.rafaelgaidzinski.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.rafaelgaidzinski.model.Cotacao;
import br.com.rafaelgaidzinski.model.Fornecedor;
import br.com.rafaelgaidzinski.model.Produto;

@Repository
public interface CotacaoRepository extends JpaRepository<Cotacao, Long> {
    boolean existsByProdutoAndFornecedor(Produto produto, Fornecedor fornecedor);
}
