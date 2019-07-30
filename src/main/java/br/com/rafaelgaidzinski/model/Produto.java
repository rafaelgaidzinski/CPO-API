package br.com.rafaelgaidzinski.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@SequenceGenerator(name = "produto_generator", sequenceName = "produto_seq", allocationSize = 1)
public class Produto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produto_generator")
    private Long id;

    @NotNull(message = "Campo nome obrigatório")
    private String nome;

    @NotNull(message = "Campo data inicial obrigatório")
    @Temporal(TemporalType.DATE)
    private Date dataInicial;

    @NotNull(message = "Campo data final obrigatório")
    @Temporal(TemporalType.DATE)
    private Date dataFinal;

    @OneToMany(mappedBy = "produto")
    private List<Cotacao> cotacoes = new ArrayList<>();

    public Produto() {
    }

    @JsonIgnore
    public List<Cotacao> getCotacoes() {
        return cotacoes;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }
}
