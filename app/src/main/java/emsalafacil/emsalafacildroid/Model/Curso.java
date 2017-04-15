package emsalafacil.emsalafacildroid.Model;

/**
 * Created by camil on 08/04/2017.
 */

public class Curso
{
    public Curso(){}

    private int idCurso;
    private String nomeCurso;
    private Turma mTurma;

    public Turma getmTurma() {
        return mTurma;
    }

    public void setmTurma(Turma mTurma) {
        this.mTurma = mTurma;
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
