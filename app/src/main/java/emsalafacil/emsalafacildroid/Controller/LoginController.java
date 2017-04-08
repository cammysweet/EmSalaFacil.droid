package emsalafacil.emsalafacildroid.Controller;

import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.view.View;

import emsalafacil.emsalafacildroid.Model.Aluno;
import emsalafacil.emsalafacildroid.R;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * Created by camil on 08/04/2017.
 */

public class LoginController
{

    public boolean isEmailValid(String email) {
        //Para matrícula (teria que puxar informações do aluno direto da api)
        if (email.length() == 10)
            return  true;
        return  false;

        //Para email (pergunta qual curso e turma do aluno)
        //return email.contains("@");
    }

    public boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }


}
