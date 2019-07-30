package br.com.rafaelgaidzinski.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@SequenceGenerator(name = "fornecedor_generator", sequenceName = "fornecedor_seq", allocationSize = 1)
public class Fornecedor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fornecedor_generator")
    private Long id;

    @Column
    @NotNull(message = "Campo nome obrigatório")
    private String nome;

    @Column
    @NotNull(message = "Campo email obrigatório")
    @Email(message = "Email inválido")
    private String email;

    @Column
    @Size(min = 10, max = 11, message = "Quantidade de dígitos do telefone inválido")
//    @Pattern(regexp = "(^$|[0-9])", message = "Telefone inválido")
    private String telefone;

    //quando excluir o fornecedor, deleta todas as cotações
    @OneToMany(mappedBy = "fornecedor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cotacao> cotacoes = new ArrayList<>();

    @JsonIgnore
    public List<Cotacao> getCotacao() {
        return cotacoes;
    }

    public Fornecedor() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }
}
