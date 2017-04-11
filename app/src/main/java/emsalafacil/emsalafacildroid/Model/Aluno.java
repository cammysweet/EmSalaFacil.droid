package emsalafacil.emsalafacildroid.Model;

/**
 * Created by camil on 08/04/2017.
 */

public class Aluno
{
    public Aluno(){}

    private int matricula;
    private String nomeAluno;
    private String status;
    private Ensalamento ensalamento;
    private Turma turma;
    private Curso curso;

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
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
