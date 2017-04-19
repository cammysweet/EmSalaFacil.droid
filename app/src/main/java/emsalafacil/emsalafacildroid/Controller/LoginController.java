package emsalafacil.emsalafacildroid.Controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import emsalafacil.emsalafacildroid.Model.*;
import emsalafacil.emsalafacildroid.R;
import emsalafacil.emsalafacildroid.Util;

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
            URL apiEnd = new URL(urlApi+"/user/userIsValid/"+matricula+"/"+password);
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

    public List<String> GetUsuariosCadastrados()
    {

        try
        {
            //*************************API******************************
//            URL apiEnd = new URL(urlApi+"/user/getUsers");
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
//            String json = Util.rawToJson(is);
//
//            Type listType = new TypeToken<ArrayList<Usuario>>(){}.getType();
//
//            List<Usuario> usuarios = new Gson().fromJson(json, listType);
//
//            is.close();
//            conexao.disconnect();
//
//            List<String> retorno = new ArrayList<String>();
//            for (Usuario u:usuarios)
//            {
//                String x = u.getEmail()+":"+u.getSenha();
//                retorno.add(x);
//            }
            //*************************FIM API******************************

            //*************************FAKE****************************
            List<String> retorno = new ArrayList<String>();
            String x = "1201500669:1201500669";
            String y = "1201500663:1201500663";
            retorno.add(x);
            retorno.add(y);
            //*************************FIM FAKE****************************

            return retorno;
        }
//        catch (MalformedURLException e)
//        {
//            return null;
//
//        }
//        catch (IOException e)
//        {
//            return null;
//        }
        catch(Exception e)
        {
            return null;
        }
    }
}
