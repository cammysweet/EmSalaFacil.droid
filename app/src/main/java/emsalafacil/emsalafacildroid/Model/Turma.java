package emsalafacil.emsalafacildroid.Model;

import java.util.List;

import emsalafacil.emsalafacildroid.Controller.AlunoController;
import emsalafacil.emsalafacildroid.Controller.TurmaDisciplinaController;

/**
 * Created by camil on 08/04/2017.
 */

public class Turma
{
    AlunoController alunoController = new AlunoController();
    TurmaDisciplinaController turmaDisciplinaController = new TurmaDisciplinaController();

    public Turma(){}
    public Turma(int idTurma)
    {
//        this.alunos = alunoController.getAlunosByTurma(idTurma);
//        this.turmasDisciplinas = turmaDisciplinaController.GetTurmaDisciplina(idTurma);
    }
    public Turma(int idTurma, int qtdAlunos, String descricao)
    {
        this.idTurma = idTurma;
        this.quantidadeAlunos = qtdAlunos;
        this.descricao = descricao;
//        this.alunos = alunoController.getAlunosByTurma(idTurma);
//        this.turmasDisciplinas = turmaDisciplinaController.GetTurmaDisciplina(idTurma);
    }

    private int idTurma;
    private int quantidadeAlunos;
    private String descricao;
//    private List<Usuario> alunos;
//    private List<TurmaDisciplina> turmasDisciplinas;

//    public List<Usuario> getAlunos() {
//        return alunos;
//    }
//
//    public void setAlunos(List<Usuario> alunos) {
//        this.alunos = alunos;
//    }
//
//    public List<TurmaDisciplina> getTurmasDisciplinas() {
//        return turmasDisciplinas;
//    }
//
//    public void setTurmasDisciplinas(List<TurmaDisciplina> turmasDisciplinas) {
//        this.turmasDisciplinas = turmasDisciplinas;
//    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getIdTurma() {
        return idTurma;
    }

//    public void setIdTurma(int idTurma) {
//        this.idTurma = idTurma;
//    }
//
//    public int getQuantidadeAlunos() {
//        return quantidadeAlunos;
//    }

    public void setQuantidadeAlunos(int quantidadeAlunos) {
        this.quantidadeAlunos = quantidadeAlunos;
    }
}
