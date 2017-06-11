package emsalafacil.emsalafacildroid.Controller;

import com.google.gson.Gson;

import org.json.JSONObject;

import emsalafacil.emsalafacildroid.Model.Disciplina;
import emsalafacil.emsalafacildroid.Model.Ensalamento;
import emsalafacil.emsalafacildroid.Model.Sala;
import emsalafacil.emsalafacildroid.Model.Usuario;
import emsalafacil.emsalafacildroid.Util;

/**
 * Created by etson on 10/04/2017.
 */

public class EnsalamentoController
{



    public Ensalamento JsonToEnsalamento(String json)
    {
        Ensalamento ensalamento;

        try
        {
            ensalamento = new Ensalamento();
            JSONObject mainObject = new JSONObject(json);
            Gson gson = new Gson();

            ensalamento.setId(mainObject.getInt("Id"));
            ensalamento.setIdTurma(mainObject.getInt("IdTurma"));
            ensalamento.setDatainicio(Util.StringToDate(mainObject.getString("DataInicio")));
            ensalamento.setDataFim(Util.StringToDate(mainObject.getString("DataFim")));
            ensalamento.setDiaSemana(mainObject.getString("DiaSemana"));
            ensalamento.setDisponibilidadeProfessor(mainObject.getBoolean("DisponibilidadeProfessor"));
            ensalamento.setTurno(mainObject.getString("Turno"));
            ensalamento.setDisciplina(gson.fromJson(mainObject.getString("Disciplina"),
                    Disciplina.class));
            ensalamento.setSala(gson.fromJson(mainObject.getString("Sala"), Sala.class));
            ensalamento.setProfessor(gson.fromJson(mainObject.getString("Professor"), Usuario.class));

            return ensalamento;
        }
        catch (Exception e)
        {
            return  null;
        }
    }
}
