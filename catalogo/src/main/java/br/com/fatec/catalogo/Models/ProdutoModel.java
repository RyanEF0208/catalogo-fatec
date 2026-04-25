package br.com.fatec.catalogo.Models;
import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_Produtos")
public  class ProdutoModel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idProduto;

    @NotBlank(message = "deu errado ae parceiro!")
    @Size(min = 2, max = 1000, message = "Precisa ser maior")
    @Column(unique = true)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "id_categoria", nullable = false)
    private CategoriaModel categoria;

    @NotNull(message = "Valor obrigatório")
    @DecimalMin(value = "0.0", message = "Valor deve ser maior que 0")
    private BigDecimal valor;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dataCadastro;

    @PrePersist
    public void antesDeSalvar() {
        this.dataCadastro = LocalDateTime.now();
    }

    //gets e setters

    public Long getIdProduto() {
        return idProduto;
    }
    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }


    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public CategoriaModel getCategoria() {return categoria;}

    public void setCategoria(CategoriaModel categoria) {this.categoria = categoria;}

    public BigDecimal getValor() {
        return valor;
    }
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}


