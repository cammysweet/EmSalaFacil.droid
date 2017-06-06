package emsalafacil.emsalafacildroid.Controller;
import emsalafacil.emsalafacildroid.Model.Autenticacao;
import emsalafacil.emsalafacildroid.Model.LoginCommand;
import emsalafacil.emsalafacildroid.Model.Usuario;

/**
 * Created by camil on 08/04/2017.
 */

public class LoginController
{
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


    public boolean loginFacebook(String idFacebook)
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

    public  boolean loginNativo(LoginCommand login)
    {
        AlunoLogado = new AlunoController().LoginNativo(login);
        if (AlunoLogado == null)
            return false;

        Autenticacao.setLogado(true);
        Autenticacao.setLogadoFacebook(false);
        Autenticacao.setUsuarioLogado(AlunoLogado);
        return  true;
    }
}
