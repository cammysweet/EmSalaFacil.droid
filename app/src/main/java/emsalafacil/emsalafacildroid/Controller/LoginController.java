package emsalafacil.emsalafacildroid.Controller;

import com.facebook.login.LoginManager;
import emsalafacil.emsalafacildroid.Model.Autenticacao;

/**
 * Created by etson on 08/04/2017.
 */

public class LoginController
{
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

    public static void logout()
    {
        if(LoginManager.getInstance() != null)
            LoginManager.getInstance().logOut();

        Autenticacao.setUsuarioLogado(null);
        Autenticacao.setVinculadoFacebook(false);
    }


}
