package emsalafacil.emsalafacildroid.Controller;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import emsalafacil.emsalafacildroid.Model.Ensalamento;
import emsalafacil.emsalafacildroid.R;
import emsalafacil.emsalafacildroid.Util;

/**
 * Created by camil on 10/04/2017.
 */

public class EnsalamentoController
{
    String urlApi = String.valueOf(R.string.urlApi);

    public Ensalamento GetEnsalamentoByTurma(int idTurrma)
    {
        Ensalamento ensalamento;
        String retorno;

        try
        {
            URL apiEnd = new URL(urlApi + "/ensalamento/getEnsalamento/"+idTurrma);
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

            ensalamento = JsonToEnsalamento(retorno);

            return ensalamento;
        }
        catch(Exception e)
        {
            return null;
        }
    }

    public Ensalamento JsonToEnsalamento(String json)
    {
        Ensalamento ensalamento;

        try
        {
            ensalamento = new Ensalamento();

            return ensalamento;
        }
        catch (Exception e)
        {
            return  null;
        }
    }
}
