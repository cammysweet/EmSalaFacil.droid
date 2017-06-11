package emsalafacil.emsalafacildroid.Controller;

import com.google.gson.Gson;

import org.json.JSONObject;

import emsalafacil.emsalafacildroid.Model.Sala;
import emsalafacil.emsalafacildroid.Model.TipoSala;

/**
 * Created by camil on 15/04/2017.
 */

public class SalaController
{
    public Sala JsonToSala(String json)
    {
        Sala sala;
        Gson gson = new Gson();
        try
        {
            JSONObject mainObject = new JSONObject(json);
            sala = new Sala();
            sala.setCapacidade(mainObject.getInt("Capacidade"));
            sala.setNome(mainObject.getString("Nome"));
            sala.setId(mainObject.getInt("Id"));
            sala.setTipoDeSala(gson.fromJson(mainObject.getString("TipoSala"),
                    TipoSala.class));
            return sala;

        }
        catch (Exception e)
        {
            return null;
        }
    }
}
