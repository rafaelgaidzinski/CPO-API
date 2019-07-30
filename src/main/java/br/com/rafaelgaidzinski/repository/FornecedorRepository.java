package br.com.rafaelgaidzinski.repository;

import br.com.rafaelgaidzinski.model.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
}
