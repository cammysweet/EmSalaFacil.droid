package emsalafacil.emsalafacildroid.Controller;

import org.json.JSONObject;

import emsalafacil.emsalafacildroid.Model.Curso;

/**
 * Created by etson on 10/04/2017.
 */

public class CursoController
{

    public Curso JsonToCurso(String json)
    {
        Curso curso;

        try
        {
            curso = new Curso();
            JSONObject mainObject = new JSONObject(json);
            curso.setId(mainObject.getInt("Id"));
            curso.setNome(mainObject.getString("Nome"));
            return curso;
        }
        catch (Exception e)
        {
            return null;
        }
    }
}
