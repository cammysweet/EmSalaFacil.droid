package emsalafacil.emsalafacildroid.Model;

/**
 * Created by camil on 24/05/2017.
 */

public class Autenticacao
{
    private static boolean Logado;
    private static boolean LogadoFacebook;
    private static Usuario UsuarioLogado;
    private static Boolean vinculadoFacebook;

    public static boolean isLogado() {
        return Logado;
    }

    public static void setLogado(boolean logado) {
        Logado = logado;
    }

    public static boolean isLogadoFacebook() {
        return LogadoFacebook;
    }

    public static void setLogadoFacebook(boolean logadoFacebook) { LogadoFacebook = logadoFacebook; }

    public static Usuario getUsuarioLogado() {
        return UsuarioLogado;
    }

    public static void setUsuarioLogado(Usuario usuarioLogado) { UsuarioLogado = usuarioLogado; }

    public static Boolean getVinculadoFacebook() { return vinculadoFacebook; }

    public static void setVinculadoFacebook(Boolean _vinculadoFacebook){ vinculadoFacebook = _vinculadoFacebook; }
}
