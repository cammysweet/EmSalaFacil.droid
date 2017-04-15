package emsalafacil.emsalafacildroid.Model;

import java.util.List;

import emsalafacil.emsalafacildroid.Controller.AlunoController;
import emsalafacil.emsalafacildroid.Controller.DisciplinaController;

/**
 * Created by camil on 08/04/2017.
 */

public class Curso
{
    DisciplinaController disciplinaController = new DisciplinaController();
    AlunoController anuloController = new AlunoController();

    public Curso(){}
    public Curso(int idCurso,
            String nomeCurso)
    {
        this.idCurso = idCurso;
        this.nomeCurso = nomeCurso;
        this.disciplinas = disciplinaController.GetDisciplinasByCurso(idCurso);
        this.usuarios = anuloController.getAlunosByCurso(idCurso);
    }

    private int idCurso;
    private String nomeCurso;
    private List<Disciplina> disciplinas;
    private List<Usuario> usuarios;

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    public int getId() {
        return idCurso;
    }

    public void setId(int id) {
        this.idCurso = id;
    }

    public String getNome() {
        return nomeCurso;
    }

    public void setNome(String nome) {
        nomeCurso = nome;
    }


}
