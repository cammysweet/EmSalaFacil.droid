package emsalafacil.emsalafacildroid.Controller;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import emsalafacil.emsalafacildroid.Model.Ensalamento;
import emsalafacil.emsalafacildroid.Model.Turma;
import emsalafacil.emsalafacildroid.R;
import emsalafacil.emsalafacildroid.Util;

/**
 * Created by camil on 10/04/2017.
 */

public class TurmaController
{
    String urlApi = String.valueOf(R.string.urlApi);

    public Turma GetTurmaByMatricula(String matricula)
    {
        Turma turma;
        String retorno;

        try
        {
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

            return turma;
        }
        catch(Exception e)
        {
            return null;
        }
    }

    public Turma JsonToTurma(String json)
    {
        Turma turma;

        try
        {
            turma = new Turma();
            JSONObject mainObject = new JSONObject(json);

            turma.setDescricao(mainObject.getString("descricao"));
            turma.setIdTurma(mainObject.getInt("idTurma"));
            Ensalamento ensalamento = new EnsalamentoController().GetEnsalamentoByTurma(turma.getIdTurma());
            turma.setEnsalamento(ensalamento);
            turma.setQuantidadeAlunos(mainObject.getInt("quantidadeAlunos"));

            return turma;
        }
        catch (Exception e)
        {
            return null;
        }
    }
}
