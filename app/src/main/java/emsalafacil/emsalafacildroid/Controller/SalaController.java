package emsalafacil.emsalafacildroid.Controller;

import emsalafacil.emsalafacildroid.Model.Sala;
import emsalafacil.emsalafacildroid.enumeradores.TipoSala;

/**
 * Created by camil on 15/04/2017.
 */

public class SalaController
{
    public Sala JsonToSala(String json)
    {
        //TODO implementar método
        return null;
    }

    public Sala GetSalaFake()
    {
        Sala sala = new Sala();
        sala.setIdSala(1);;
        sala.setCapacidade(50);;
        sala.setTipoDeSala(TipoSala.LABORATORIO);
        return sala;
    }
}
