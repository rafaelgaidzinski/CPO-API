package br.com.rafaelgaidzinski.endpoint;


import br.com.rafaelgaidzinski.model.Cotacao;
import br.com.rafaelgaidzinski.repository.CotacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("cotacao")
public class CotacaoController {
    private final CotacaoRepository repository;

    @Autowired
    public CotacaoController(CotacaoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<?> listAll(Pageable pageable) {
        return new ResponseEntity<>(repository.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Long id) {
        Cotacao cotacao = repository.findById(id).get();
        return new ResponseEntity<>(cotacao, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Cotacao cotacao) throws Exception {
        if (repository.existsByProdutoAndFornecedor(cotacao.getProduto(), cotacao.getFornecedor())) {
            throw new Exception("Fornecedor pode cadastrar apenas uma cotação por produto");
        }

        Cotacao save = repository.save(cotacao);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        repository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Cotacao cotacao) {
        repository.save(cotacao);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
