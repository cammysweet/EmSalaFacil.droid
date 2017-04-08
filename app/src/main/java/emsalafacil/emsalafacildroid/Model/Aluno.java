package emsalafacil.emsalafacildroid.Model;

/**
 * Created by camil on 08/04/2017.
 */

public class Aluno
{
    public Aluno(long matricula, String nome, Turma turma)
    {
        setMatricula(matricula);
        setNome(nome);
        setTurma(turma);
    }

    long matricula;
    String nome;
    Turma turma;

    public long getMatricula() {
        return matricula;
    }

    public void setMatricula(long matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }


}
