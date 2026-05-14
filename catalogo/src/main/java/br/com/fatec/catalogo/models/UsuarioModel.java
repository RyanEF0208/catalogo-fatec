package br.com.fatec.catalogo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

@Entity
@Table(name = "TB_USUARIO")
public class UsuarioModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idUsuario;

    @NotBlank(message = "O nome do Usuário é obrigatório.")
    @Size(min = 3, message = "O nome deve ter no minímo 3 caracteres.")
    private String nome;

    @NotBlank(message = "A senha é obrigatória.")
    private String senha;

    private String tipo;

    // Getters and Setters
    public long getIdUsuario() { return idUsuario; }
    public void setIdUsuario(long idUsuario) { this.idUsuario = idUsuario; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
}
