package br.com.rafaelgaidzinski.repository;

import br.com.rafaelgaidzinski.model.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    Page<Produto> findAllByNomeContaining(Pageable pageable, String nome);

    @Query("select a from Produto a where a.dataInicial <= :date and a.dataFinal >= :date")
    Page<Produto> findAllProdutosDisponiveis(Pageable pageable, @Param("date") Date date);

    @Query("SELECT p FROM Produto p WHERE p.id in ((SELECT c.produto FROM Cotacao c))")
    Page<Produto> findAllProdutosComCotacao(Pageable pageable);

    @Query("SELECT p FROM Produto p WHERE p.id in ((SELECT c.produto FROM Cotacao c)) and p.nome LIKE CONCAT('%',:nome,'%')")
    Page<Produto> findAllProdutosComCotacaoFiltrandoPorNome(Pageable pageable, @Param("nome") String nome);
}
