package emsalafacil.emsalafacildroid.Model;

/**
 * Created by camil on 10/04/2017.
 */

public class Disciplina
{
    private int idDisciplina;
    private String descricaoDisciplinas;
    private Ensalamento ensalamento;

    public int getIdDisciplina() {
        return idDisciplina;
    }

    public void setIdDisciplina(int idDisciplina) {
        this.idDisciplina = idDisciplina;
    }

    public String getDescricaoDisciplinas() {
        return descricaoDisciplinas;
    }

    public void setDescricaoDisciplinas(String descricaoDisciplinas) {
        this.descricaoDisciplinas = descricaoDisciplinas;
    }

    public Ensalamento getEnsalamento() {
        return ensalamento;
    }

    public void setEnsalamento(Ensalamento ensalamento) {
        this.ensalamento = ensalamento;
    }


}
