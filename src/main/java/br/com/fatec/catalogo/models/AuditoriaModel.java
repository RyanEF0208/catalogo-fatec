package br.com.fatec.catalogo.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_AUDITORIA")
public class AuditoriaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String usuario;

    private String acao;

    private Long idProduto;

    private String produto;

    private Integer quantidade;

    private LocalDateTime data;

    private boolean estoqueBaixo;

    public AuditoriaModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public boolean isEstoqueBaixo() {
        return estoqueBaixo;
    }

    public void setEstoqueBaixo(boolean estoqueBaixo) {
        this.estoqueBaixo = estoqueBaixo;
    }
}