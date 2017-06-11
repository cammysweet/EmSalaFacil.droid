package emsalafacil.emsalafacildroid.Controller;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import emsalafacil.emsalafacildroid.Model.Curso;
import emsalafacil.emsalafacildroid.R;
import emsalafacil.emsalafacildroid.Util;

/**
 * Created by camil on 10/04/2017.
 */

public class CursoController
{
    String urlApi = String.valueOf(R.string.urlApi);

    public Curso GetCursoByMatricula(int matricula)
    {
        Curso curso;
        String retorno;

        try
        {
//            URL apiEnd = new URL(urlApi + "/aluno/getAluno/"+matricula);
//            int codigoResposta;
//            HttpURLConnection conexao;
//            InputStream is;
//
//            conexao = (HttpURLConnection) apiEnd.openConnection();
//            conexao.setRequestMethod("GET");
//            conexao.setReadTimeout(15000);
//            conexao.setConnectTimeout(15000);
//            conexao.connect(); //InvocationTargetException
//
//            codigoResposta = conexao.getResponseCode();
//            if (codigoResposta < HttpURLConnection.HTTP_BAD_REQUEST)
//                is = conexao.getInputStream();
//            else
//                is = conexao.getErrorStream();
//
//            retorno = Util.rawToJson(is);
//
//            curso = JsonToCurso(retorno);

            curso = GetCursoFake();

            return curso;
        }
        catch(Exception e)
        {
            return null;
        }
    }

    public Curso JsonToCurso(String json)
    {
        Curso curso;

        try
        {
            curso = new Curso();
            JSONObject mainObject = new JSONObject(json);
            curso.setId(mainObject.getInt("idCurso"));
            curso.setNome(mainObject.getString("nomeCurso"));
            return curso;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    private Curso GetCursoFake()
    {
        Curso curso = new Curso();
        curso.setId(1);
        curso.setNome("Análise e desenvolvimento de sistemas");
        return curso;
    }
}
