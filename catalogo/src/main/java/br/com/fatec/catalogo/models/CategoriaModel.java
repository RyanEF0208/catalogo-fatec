package br.com.fatec.catalogo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

@Entity
@Table(name = "TB_CATEGORIA")
public class CategoriaModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idCategoria;

    @NotBlank(message = "O nome da categoria é obrigatório.")
    @Size(min = 3, message = "O nome deve ter no minímo 3 caracteres.")
    @Column(unique = true)
    private String nome;

    public CategoriaModel() {}

    // Getters and Setters
    public long getIdCategoria() { return idCategoria; }
    public void setIdCategoria(long idCategoria) { this.idCategoria = idCategoria; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
}
