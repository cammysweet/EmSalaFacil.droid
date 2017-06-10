package emsalafacil.emsalafacildroid.Controller;

import org.json.JSONObject;

import emsalafacil.emsalafacildroid.Model.Turma;

/**
 * Created by camil on 10/04/2017.
 */

public class TurmaController
{

    public Turma JsonToTurma(String json)
    {
        Turma turma;

        try
        {
            turma = new Turma();

            JSONObject mainObject = new JSONObject(json);
            turma.setId(mainObject.getInt("Id"));
            turma.setDescricao(mainObject.getString("Descricao"));
            turma.setQuantidadeAlunos(mainObject.getInt("QuantidadeAlunos"));
            return  turma;
        }
        catch(Exception e)
        {
            return null;
        }
    }
}
