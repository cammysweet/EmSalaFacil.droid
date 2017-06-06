package emsalafacil.emsalafacildroid.Model;

/**
 * Created by camil on 14/04/2017.
 */

public class Usuario
{
    public Usuario(){}

    private int id;
    private String matricula;
    private int tipoUsuarioId;
    private TipoUsuario tipoUsuario;
    private String nome;
    private String email;
    private String senha;
    private String idFacebook;
    private int idCurso;
    private Curso curso;
    private int idTurma;

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public int getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(int idTurma) {
        this.idTurma = idTurma;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    private Turma turma;

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

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