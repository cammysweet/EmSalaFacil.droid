package emsalafacil.emsalafacildroid.Controller;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import emsalafacil.emsalafacildroid.Model.Turma;
import emsalafacil.emsalafacildroid.R;
import emsalafacil.emsalafacildroid.Util;

/**
 * Created by camil on 10/04/2017.
 */

public class TurmaController
{
    String urlApi = String.valueOf(R.string.urlApi);
    AlunoController alunoController = new AlunoController();
    LoginController loginController = new LoginController();

    public Turma GetTurmaByMatricula(int matricula)
    {
        Turma turma;
        String retorno;

        try
        {
            //***************************************************************
            URL apiEnd = new URL(urlApi + "/turma/getTurma/"+matricula);
            int codigoResposta;
            HttpURLConnection conexao;
            InputStream is;

            conexao = (HttpURLConnection) apiEnd.openConnection();
            conexao.setRequestMethod("GET");
            conexao.setReadTimeout(15000);
            conexao.setConnectTimeout(15000);
            conexao.connect(); //InvocationTargetException

            codigoResposta = conexao.getResponseCode();
            if (codigoResposta < HttpURLConnection.HTTP_BAD_REQUEST)
                is = conexao.getInputStream();
            else
                is = conexao.getErrorStream();

            retorno = Util.rawToJson(is);

            turma = JsonToTurma(retorno);
            //***************************************************************

            return turma;
        }
        catch(Exception e)
        {
            return null;
        }
    }

    private Turma JsonToTurma(String json)
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
//    private Turma GetTurmaFake()
//    {
//        Turma turma = new Turma(1);
//        turma.setQuantidadeAlunos(50);
//        turma.setDescricao("TDS151A");
//        return turma;
//    }

//    public Turma GetTurmaById(int idTurma)
//    {
//        // implementar método
//        return null;
//    }
}
