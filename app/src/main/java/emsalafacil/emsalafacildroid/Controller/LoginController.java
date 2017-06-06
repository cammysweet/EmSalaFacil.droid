package emsalafacil.emsalafacildroid.Controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import emsalafacil.emsalafacildroid.Model.Autenticacao;
import emsalafacil.emsalafacildroid.Model.Usuario;
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
        return password.length() > 4;
    }

    public boolean isLoginFacebookOk(String idFacebook)
    {
        try
        {
            AlunoLogado = new AlunoController().GetAlunoByIdFacebook(idFacebook);
            if(AlunoLogado == null)
                return false;

            Autenticacao.setLogadoFacebook(true);
            Autenticacao.setLogado(true);
            Autenticacao.setUsuarioLogado(AlunoLogado);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public  boolean isLoginApiOk(String matricula, String password)
    {
        AlunoLogado = new AlunoController().GetAlunoByMatricula(matricula);
        if (AlunoLogado != null)
        {
            Autenticacao.setLogado(true);
            Autenticacao.setLogadoFacebook(false);
            Autenticacao.setUsuarioLogado(AlunoLogado);
            return  true;
        }

        return false;
    }


    public List<String> GetUsuariosCadastrados()
    {

        try
        {
            URL apiEnd = new URL(urlApi+"/Usuario/Pesquisar");
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

            String json = Util.rawToJson(is);

            Type listType = new TypeToken<ArrayList<Usuario>>(){}.getType();

            List<Usuario> usuarios = new Gson().fromJson(json, listType);

            is.close();
            conexao.disconnect();

            List<String> retorno = new ArrayList<String>();
            for (Usuario u:usuarios)
            {
                String x = u.getEmail()+":"+u.getSenha();
                retorno.add(x);
            }
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
            List<String> retorno = new ArrayList<String>();
            String x = "1201500669:1201500669";
            String y = "1201500663:1201500663";
            retorno.add(x);
            retorno.add(y);

            return retorno;
        }
    }
}
