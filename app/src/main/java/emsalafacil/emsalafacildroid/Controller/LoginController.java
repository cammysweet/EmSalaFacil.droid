package emsalafacil.emsalafacildroid.Controller;
import emsalafacil.emsalafacildroid.Model.Usuario;

/**
 * Created by camil on 08/04/2017.
 */

public class LoginController
{
    static Usuario AlunoLogado;
    public static Usuario getAlunoLogado() { return AlunoLogado; }
    public static void setAlunoLogado(Usuario alunoLogado){ AlunoLogado = alunoLogado; }

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


}
