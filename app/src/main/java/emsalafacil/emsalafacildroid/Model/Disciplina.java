package emsalafacil.emsalafacildroid.Model;

import java.util.List;

import emsalafacil.emsalafacildroid.Controller.TurmaDisciplinaController;

/**
 * Created by camil on 10/04/2017.
 */

public class Disciplina
{
    public Disciplina(){}
    public Disciplina( int idDisciplina, String descricaoDisciplina,
            Curso curso)
    {
        this.idDisciplina = idDisciplina;
        this.descricaoDisciplina = descricaoDisciplina;
        //this.turmasDisciplinas = turmaDisciplinaController.getTurmaDisciplina();
        this.curso = curso;
    }

    private int idDisciplina;
    private String descricaoDisciplina;
    private List<TurmaDisciplina> turmasDisciplinas;
    private Curso curso;

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public List<TurmaDisciplina> getTurmasDisciplinas() {
        return turmasDisciplinas;
    }

    public void setTurmasDisciplinas(List<TurmaDisciplina> turmasDisciplinas) {
        this.turmasDisciplinas = turmasDisciplinas;
    }

    public int getIdDisciplina() {
        return idDisciplina;
    }

    public void setIdDisciplina(int idDisciplina) {
        this.idDisciplina = idDisciplina;
    }

    public String getDescricaoDisciplina() {
        return descricaoDisciplina;
    }

    public void setDescricaoDisciplina(String descricaoDisciplina) {
        this.descricaoDisciplina = descricaoDisciplina;
    }


}
