package emsalafacil.emsalafacildroid.Model;

import java.util.Date;

/**
 * Created by etson on 10/04/2017.
 */

public class Ensalamento
{
    private int id;
    private int idTurma;
    private String turno;
    private Date datainicio;
    private Date dataFim;
    private String diaSemana;
    private Boolean disponibilidadeProfessor;
    private Curso turma;
    private Disciplina disciplina;
    private Sala sala;
    private Usuario professor;

    public Usuario getProfessor() {
        return professor;
    }

    public void setProfessor(Usuario professor) {
        this.professor = professor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(int idTurma) {
        this.idTurma = idTurma;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
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

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public Boolean getDisponibilidadeProfessor() {
        return disponibilidadeProfessor;
    }

    public void setDisponibilidadeProfessor(Boolean disponibilidadeProfessor) {
        this.disponibilidadeProfessor = disponibilidadeProfessor;
    }

    public Curso getTurma() {
        return turma;
    }

    public void setTurma(Curso turma) {
        this.turma = turma;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

}
