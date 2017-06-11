package emsalafacil.emsalafacildroid.Model;

/**
 * Created by camil on 10/04/2017.
 */

public class Sala
{
    private int id;
    private int capacidade;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    private String nome;
    private TipoSala tipoSala;

    private String Descricao;

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        this.Descricao = descricao;
    }

    public TipoSala getTipoDeSala() {
        return tipoSala;
    }

    public void setTipoDeSala(TipoSala tipoDeSala) {
        this.tipoSala = tipoDeSala;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }


}
