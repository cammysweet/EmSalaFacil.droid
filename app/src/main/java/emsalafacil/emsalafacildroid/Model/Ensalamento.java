package emsalafacil.emsalafacildroid.Model;

import java.util.Date;

import emsalafacil.emsalafacildroid.enumeradores.Turno;

/**
 * Created by camil on 10/04/2017.
 */

public class Ensalamento
{
    private int idEnsalamento;
    private Turno turno;
    private Date datainicio;
    private Date dataFim;
    private String diaDaSemana;

    public int getIdEnsalamento() {
        return idEnsalamento;
    }

    public void setIdEnsalamento(int idEnsalamento) {
        this.idEnsalamento = idEnsalamento;
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    public Date getDatainicio() {
        return datainicio;
    }

    public void setDatainicio(Date datainicio) {
        this.datainicio = datainicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public String getDiaDaSemana() {
        return diaDaSemana;
    }

    public void setDiaDaSemana(String diaDaSemana) {
        this.diaDaSemana = diaDaSemana;
    }

    public String getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(String disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    private String disponibilidade;
}
