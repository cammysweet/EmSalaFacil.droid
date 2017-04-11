package emsalafacil.emsalafacildroid.Model;

/**
 * Created by camil on 10/04/2017.
 */

public class TipoDeSala
{
    private int idTipoSala;
    private String nomeTipo;
    private Sala mSala;

    public int getIdTipoSala() {
        return idTipoSala;
    }

    public void setIdTipoSala(int idTipoSala) {
        this.idTipoSala = idTipoSala;
    }

    public String getNomeTipo() {
        return nomeTipo;
    }

    public void setNomeTipo(String nomeTipo) {
        this.nomeTipo = nomeTipo;
    }

    public Sala getmSala() {
        return mSala;
    }

    public void setmSala(Sala mSala) {
        this.mSala = mSala;
    }
}
