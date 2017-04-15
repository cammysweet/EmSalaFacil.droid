package emsalafacil.emsalafacildroid.Controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import emsalafacil.emsalafacildroid.Model.*;
import emsalafacil.emsalafacildroid.R;

/**
 * Created by camil on 08/04/2017.
 */

public class LoginController
{
    String urlApi = String.valueOf(R.string.urlApi);

    static Usuario AlunoLogado;

    public static Usuario getAlunoLogado() { return AlunoLogado; }

    public boolean isMatriculaValid(String matricula)
    {
        if (matricula.length() == 10)
            return  true;
        return  false;
    }

    public boolean isPasswordValid(String password)
    {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    public  boolean isLoginApiOk(String matricula, String password)
    {
        //TODO testar endpoint e ajustar returns em caso de erro.
        boolean ok = true; //TODO false; - quando API estiver ok voltar o ok = false;
        //String retorno = "";
        try
        {
            URL apiEnd = new URL(urlApi+"/"+matricula+"/"+password);
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
            {
                is = conexao.getInputStream();
                AlunoLogado = new AlunoController().GetAlunoByMatricula(matricula);
                ok = true;
            }
            else
            {
                is = conexao.getErrorStream();
                ok = true; //TODO false; - quando API estiver ok voltar o ok = false;
            }

            //retorno = Util.rawToJson(is);
            is.close();
            conexao.disconnect();

        }
        catch (MalformedURLException e)
        {
            AlunoLogado = new AlunoController().GetAlunoByMatricula(matricula);
           return true;

        }
        catch (IOException e)
        {
            return true;
        }

        return ok;
    }


}
