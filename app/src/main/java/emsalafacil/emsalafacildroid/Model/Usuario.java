package emsalafacil.emsalafacildroid.Model;

/**
 * Created by natha on 14/04/2017.
 */

public class Usuario
{
    private int matricula;
    private String nomeCompleto;
    private String tipoUsuario;
    private String email;
    private String senha;
    private Sala sala;
    private Turma turma;
    private String status;
    private Ensalamento ensalamento;

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
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

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Ensalamento getEnsalamento() {
        return ensalamento;
    }

    public void setEnsalamento(Ensalamento ensalamento) {
        this.ensalamento = ensalamento;
    }



}
