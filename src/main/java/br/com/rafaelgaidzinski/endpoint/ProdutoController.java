package br.com.rafaelgaidzinski.endpoint;

import br.com.rafaelgaidzinski.model.Produto;
import br.com.rafaelgaidzinski.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.Instant;
import java.util.Date;

@RestController
@RequestMapping("produto")
public class ProdutoController {
    private final ProdutoRepository repository;

    @Autowired
    public ProdutoController(ProdutoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<?> listAll(@RequestParam(value = "nome", defaultValue = "", required = false) String nome,
                                     Pageable pageable) {
        Page<Produto> page = null;

        if (nome.equals("")) {
            page = repository.findAll(pageable);
        } else {
            page = repository.findAllByNomeContaining(pageable, nome);
        }
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @GetMapping("com-cotacao")
    public ResponseEntity<?> listAllProdutosComCotacao(@RequestParam(value = "nome", defaultValue = "", required = false) String nome,
                                                       Pageable pageable) {
        Page<Produto> page = null;

        if (nome.equals("")) {
            page = repository.findAllProdutosComCotacao(pageable);
        } else {
            page = repository.findAllProdutosComCotacaoFiltrandoPorNome(pageable, nome);
        }
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @GetMapping("disponiveis")
    public ResponseEntity<?> listAllDisponiveis(@RequestParam(value = "nome", defaultValue = "", required = false) String nome,
                                                Pageable pageable) {
        Page<Produto> page = repository.findAllProdutosDisponiveis(pageable, Date.from(Instant.now()));
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable("id") Long id) {
        Produto produto = repository.findById(id).get();
        return new ResponseEntity<>(produto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Produto produto) {
        Produto save = repository.save(produto);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        repository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Produto produto) {
        repository.save(produto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
