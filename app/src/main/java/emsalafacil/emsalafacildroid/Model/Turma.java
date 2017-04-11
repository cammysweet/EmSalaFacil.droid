package emsalafacil.emsalafacildroid.Model;

/**
 * Created by camil on 08/04/2017.
 */

public class Turma
{
    private int idTurma;
    private int quantidadeAlunos;
    private Ensalamento ensalamento;
    private String descricao;
    private Curso curso;

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(int idTurma) {
        this.idTurma = idTurma;
    }

    public int getQuantidadeAlunos() {
        return quantidadeAlunos;
    }

    public void setQuantidadeAlunos(int quantidadeAlunos) {
        this.quantidadeAlunos = quantidadeAlunos;
    }

    public Ensalamento getEnsalamento() {
        return ensalamento;
    }

    public void setEnsalamento(Ensalamento ensalamento) {
        this.ensalamento = ensalamento;
    }
}
