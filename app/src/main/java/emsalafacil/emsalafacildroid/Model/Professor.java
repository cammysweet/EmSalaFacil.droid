package emsalafacil.emsalafacildroid.Model;

/**
 * Created by camil on 10/04/2017.
 */

public class Professor
{
    private int matricula;
    private String nomeProfessor;
    private String status;
    private Ensalamento ensalamento;

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNomeProfessor() {
        return nomeProfessor;
    }

    public void setNomeProfessor(String nomeProfessor) {
        this.nomeProfessor = nomeProfessor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Ensalamento getEnsalamento() {
        return ensalamento;
    }

    public void setEnsalamento(Ensalamento ensalamento) {
        this.ensalamento = ensalamento;
    }




}
