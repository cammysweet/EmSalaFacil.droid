package emsalafacil.emsalafacildroid.Model;

/**
 * Created by camil on 08/04/2017.
 */

public class Curso
{
    public Curso(int id, String nome)
    {
        setId(id);
        setNome(nome);
    }

    int id;
    String Nome;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }


}
