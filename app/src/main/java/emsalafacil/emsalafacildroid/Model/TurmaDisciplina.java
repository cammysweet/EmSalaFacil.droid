package emsalafacil.emsalafacildroid.Model;

/**
 * Created by camil on 15/04/2017.
 */

public class TurmaDisciplina
{
    public TurmaDisciplina(){}
    public TurmaDisciplina(Turma turma, Disciplina disciplina)
    {
        this.turma = turma;
        this.disciplina = disciplina;
    }

    private Turma turma;
    private Disciplina disciplina;

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }


}
