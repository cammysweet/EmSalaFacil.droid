package emsalafacil.emsalafacildroid.Model;

/**
 * Created by camilacardoso on 06/06/2017.
 */

public class LoginCommand
{
    private String matricula;
    private String senha;

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String email) {
        this.matricula = email;
    }


}
