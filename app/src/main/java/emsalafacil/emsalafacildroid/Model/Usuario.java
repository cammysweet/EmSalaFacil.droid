package emsalafacil.emsalafacildroid.Model;

import emsalafacil.emsalafacildroid.enumeradores.*;

/**
 * Created by camil on 14/04/2017.
 */

public class Usuario
{
    public Usuario(){}

    private int id;
    private String matricula;
    private int tipoUsuarioId;
    private String nome;
    private String email;
    private String senha;
    private String idFacebook;

    public int getTipoUsuarioId() { return tipoUsuarioId; }

    public void setTipoUsuarioId(int tipoUsuarioId) { this.tipoUsuarioId = tipoUsuarioId; }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getIdFacebook() {
        return idFacebook;
    }

    public void setIdFacebook(String idFacebook) {
        this.idFacebook = idFacebook;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) { this.senha = senha;  }


}