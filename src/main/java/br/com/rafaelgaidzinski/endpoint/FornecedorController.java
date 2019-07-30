package br.com.rafaelgaidzinski.endpoint;


import br.com.rafaelgaidzinski.model.Fornecedor;
import br.com.rafaelgaidzinski.repository.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("fornecedor")
public class FornecedorController {
    private final FornecedorRepository repository;

    @Autowired
    public FornecedorController(FornecedorRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<?> listAll(Pageable pageable) {
        return new ResponseEntity<>(repository.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Long id) {
        Fornecedor produto = repository.findById(id).get();
        return new ResponseEntity<>(produto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Fornecedor fornecedor) {
        Fornecedor save = repository.save(fornecedor);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        repository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Fornecedor fornecedor) {
        repository.save(fornecedor);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
