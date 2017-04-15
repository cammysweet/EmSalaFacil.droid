package emsalafacil.emsalafacildroid.Model;

import emsalafacil.emsalafacildroid.enumeradores.*;

/**
 * Created by camil on 14/04/2017.
 */

public class Usuario
{
    public Usuario(){}
    public Usuario(int matricula, String nome, String email, String senha,
                   Turma turma, Curso curso, Status status, TipoUsuario tipoUsuario)
    {
        this.matricula = matricula;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.turma = turma;
        this.curso = curso;
        this.status = status;
        this.tipoUsuario = tipoUsuario;
    }

    private int matricula;
    private String nome;
    private String email;
    private String senha;
    private Turma turma;
    private Curso curso;
    private Status status;
    private TipoUsuario tipoUsuario;

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
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

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
