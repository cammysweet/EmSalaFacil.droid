package emsalafacil.emsalafacildroid.Model;

/**
 * Created by camil on 08/04/2017.
 */

public class Turma
{
    public Turma(int id, String nome, Curso curso)
    {
        setId(id);
        setNome(nome);
        setCurso(curso);
    }

    int id;
    String nome;
    Curso curso;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}
