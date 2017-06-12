package emsalafacil.emsalafacildroid.Controller;
import android.content.Intent;

import com.facebook.login.LoginManager;

import emsalafacil.emsalafacildroid.Activities.EnsalamentoActivity;
import emsalafacil.emsalafacildroid.Activities.LoginV2Activity;
import emsalafacil.emsalafacildroid.Model.Usuario;

/**
 * Created by etson on 08/04/2017.
 */

public class LoginController
{
    static Usuario AlunoLogado;
    public static Usuario getAlunoLogado() { return AlunoLogado; }
    public static void setAlunoLogado(Usuario alunoLogado){ AlunoLogado = alunoLogado; }

    static Boolean vinculadoFacebook;
    public static Boolean getVinculadoFacebook() { return vinculadoFacebook; }
    public static void setVinculadoFacebook(Boolean _vinculadoFacebook){ vinculadoFacebook = _vinculadoFacebook; }

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

        LoginController.setAlunoLogado(null);
        LoginController.setVinculadoFacebook(false);
    }


}
