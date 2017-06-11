package emsalafacil.emsalafacildroid.Model;

import java.util.Date;

import emsalafacil.emsalafacildroid.enumeradores.Disponibilidade;
import emsalafacil.emsalafacildroid.enumeradores.Turno;

/**
 * Created by camil on 10/04/2017.
 */

public class Ensalamento
{
    public Ensalamento(){}
    public Ensalamento(int idEnsalamento, Turno turno, Date datainicio, Date dataFim, String diaDaSemana,
             Disponibilidade disponibilidade, Turma turma, Disciplina disciplina, Sala sala)
    {
        this.idEnsalamento = idEnsalamento;
        this.turno = turno;
        this.datainicio = datainicio;
        this.dataFim = dataFim;
        this.diaDaSemana = diaDaSemana;
        this.disponibilidade = disponibilidade;
        this.turma = turma;
        this.disciplina = disciplina;
        this.sala = sala;
    }

    private int idEnsalamento;
    private Turno turno;
    private Date datainicio;
    private Date dataFim;
    private String diaDaSemana;
    private Disponibilidade disponibilidade;
    private Turma turma;
    private Disciplina disciplina;
    private Sala sala;

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

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

    public Disponibilidade getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(Disponibilidade disponibilidade) {
        this.disponibilidade = disponibilidade;
    }


}
