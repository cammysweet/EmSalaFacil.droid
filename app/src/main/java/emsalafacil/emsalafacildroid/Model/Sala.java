package emsalafacil.emsalafacildroid.Model;

import emsalafacil.emsalafacildroid.enumeradores.TipoSala;

/**
 * Created by camil on 10/04/2017.
 */

public class Sala
{
    public Sala(){}
    public Sala(int idSala, int capacidade, TipoSala tipoSala)
    {
        this.idSala = idSala;
        this.capacidade = capacidade;
        this.tipoSala = tipoSala;
    }

    private int idSala;
    private int capacidade;
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

    public int getIdSala() {
        return idSala;
    }

    public void setIdSala(int idSala) {
        this.idSala = idSala;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }


}
