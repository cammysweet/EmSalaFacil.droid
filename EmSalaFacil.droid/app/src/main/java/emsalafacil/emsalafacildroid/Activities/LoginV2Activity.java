package emsalafacil.emsalafacildroid.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.ArraySet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.gson.Gson;

import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.ThreadFactory;

import emsalafacil.emsalafacildroid.Controller.AlunoController;
import emsalafacil.emsalafacildroid.Controller.LoginController;
import emsalafacil.emsalafacildroid.Model.Autenticacao;
import emsalafacil.emsalafacildroid.Model.LoginCommand;
import emsalafacil.emsalafacildroid.Model.Usuario;
import emsalafacil.emsalafacildroid.Model.VinculoFacebookCommand;
import emsalafacil.emsalafacildroid.R;
import emsalafacil.emsalafacildroid.Util;

public class LoginV2Activity extends AppCompatActivity {

    LoginController loginController = new LoginController();
    CallbackManager callbackManager;
    EditText entryMatricula;
    EditText entrySenha;
    Button btnLogin;
    LoginButton loginButton;
    Boolean loginFacebookOk;
    Boolean loginNativoOk;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        /// login fb
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login_v2);

        entryMatricula = (EditText) findViewById(R.id.entryMatriculav2);
        entrySenha = (EditText) findViewById(R.id.entrySenhav2);
        btnLogin = (Button) findViewById(R.id.btnLoginv2);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBtnLoginNativoClicked();
            }
        });

        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton) findViewById(R.id.btnLoginFacebookv2);

        loginButton.setReadPermissions(Arrays.asList("email","public_profile"));//, , "user_friends", "publish_actions"
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>()
        {
            @Override
            public void onSuccess(LoginResult loginResult)
            {
                Log.i("ID_FB",loginResult.getAccessToken().getUserId());
                goMainScreen(loginResult.getAccessToken().getUserId(),
                        "camila.cardoso65@hotmail.com", entryMatricula.getText().toString());

            }

            @Override
            public void onCancel()
            {
                Toast.makeText(getApplicationContext(), R.string.cancel_login, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error)
            {
                Toast.makeText(getApplicationContext(), R.string.error_login+error.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });

//        AccessToken accessToken = AccessToken.getCurrentAccessToken();
//        if(accessToken != null){
//            goMainScreen(accessToken.getUserId(),
//                    "camila.cardoso65@hotmail.com", entryMatricula.getText().toString());
//        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }


    private void goMainScreen(String idFacebook, String emailFacebook, String matricula)
    {
        //VinculoFacebookCommand vinculoCmd = new VinculoFacebookCommand();
        //vinculoCmd.setIdFacebook(idFacebook);
        //vinculoCmd.setEmailFacebook(emailFacebook);
        //vinculoCmd.setMatricula(matricula);

        if(!loginFacebook(idFacebook))
        {
            Intent intent = new Intent(LoginV2Activity.this,CompletarCadastro.class);
            intent.putExtra("FB_ID",idFacebook);
            intent.putExtra("FB_EMAIL", emailFacebook);
            startActivity(intent);
        }
        else
        {
            Intent intent = new Intent(LoginV2Activity.this,CalendarioActivity.class);
            intent.putExtra("FB_ID",idFacebook);
            intent.putExtra("FB_EMAIL", emailFacebook);
            startActivity(intent);
        }
    }

    private void onBtnLoginNativoClicked()
    {
        // Reset errors.
        entryMatricula.setError(null);
        entrySenha.setError(null);

        // Store values at the time of the login attempt.
        String matricula = entryMatricula.getText().toString();
        String password = entrySenha.getText().toString();
        LoginCommand loginObj = new LoginCommand();
        loginObj.setMatricula(matricula);
        loginObj.setSenha(password);

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password)  && !loginController.isPasswordValid(password))
        {
            entrySenha.setError(getString(R.string.error_invalid_password));
            focusView = entrySenha;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(matricula))
        {
            entryMatricula.setError(getString(R.string.error_field_required));
            focusView = entryMatricula;
            cancel = true;
        }
        else if (!loginController.isMatriculaValid(matricula))
        {
            entryMatricula.setError(getString(R.string.error_invalid_email));
            focusView = entryMatricula;
            cancel = true;
        }
        else if(!loginNativo(loginObj))
        {
            entryMatricula.setError("Matrícula ou senha inválidos.");
            focusView = entryMatricula;
            cancel = true;
        }

        if (cancel)
        {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        }
        else
        {
            Intent intent = new Intent(this, CalendarioActivity.class);
            startActivity(intent);
        }
    }

    public  Boolean loginNativo(LoginCommand login)
    {
        try
        {
            LoginController loginController = new LoginController();
            new LoginNativoApi().execute(login);
            Thread.sleep(5000);
            if (!loginNativoOk)
                return false;
            Autenticacao.setLogado(true);
            Autenticacao.setLogadoFacebook(false);
            Autenticacao.setUsuarioLogado(loginController.getAlunoLogado());

            return  true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public Boolean loginFacebook(String idFacebook)
    {
        try
        {
            LoginController loginController = new LoginController();
            new LoginFacebookApi().execute(idFacebook);
            Thread.sleep(5000);
            if(!loginFacebookOk)
                return false;
            Autenticacao.setLogadoFacebook(true);
            Autenticacao.setLogado(true);
            Autenticacao.setUsuarioLogado(loginController.getAlunoLogado());
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    private class LoginNativoApi extends AsyncTask<LoginCommand, Void, Usuario>
    {
        @Override
        protected Usuario doInBackground(LoginCommand... params)
        {
            String urlApi = "http://caiofelipe.com/api/"; // String.valueOf(R.string.urlApi);
            Gson gson = new Gson();

            try
            {
                URL apiEnd = new URL(urlApi + "usuario/login");
                int codigoResposta;
                HttpURLConnection conexao;
                InputStream is;

                conexao = (HttpURLConnection) apiEnd.openConnection();
                conexao.setRequestMethod("POST");
                conexao.setReadTimeout(15000);
                conexao.setConnectTimeout(15000);
                conexao.setDoInput(true);
                conexao.setDoOutput(true);
                OutputStreamWriter writer = new OutputStreamWriter(conexao.getOutputStream());
                String json = gson.toJson(params[0]);
                writer.write(json);
                conexao.connect(); //InvocationTargetException



                codigoResposta = conexao.getResponseCode();
                if (codigoResposta < HttpURLConnection.HTTP_BAD_REQUEST)
                {
                    is = conexao.getInputStream();
                    loginNativoOk = true;
                    return  new AlunoController().JsonToAluno(Util.rawToJson(is));
                }
                else
                {
                    conexao.getErrorStream();
                    loginNativoOk = false;
                    return  null;
                }
            }
            catch(Exception e)
            {
                return null;
            }
        }

        @Override
        protected void onPostExecute(Usuario usuario)
        {
            if(usuario != null)
            {
                LoginController loginController = new LoginController();
                loginController.setAlunoLogado(usuario);
                loginNativoOk = true;
            }
           else
                loginNativoOk = false;
        }
    }

    private class LoginFacebookApi extends  AsyncTask<String, Void, Usuario>
    {
        @Override
        protected Usuario doInBackground(String... params)
        {
            try
            {
                String urlApi = "http://caiofelipe.com/api/"; // String.valueOf(R.string.urlApi);

                URL apiEnd = new URL(urlApi + "usuario/getbyfacebook/"+params[0]);
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
                    Usuario usr = new AlunoController().JsonToAluno(Util.rawToJson(is));
                    LoginController loginController = new LoginController();
                    if(usr != null)
                    {
                        loginController.setAlunoLogado(usr);
                        loginFacebookOk = true;
                    }
                    else
                        loginFacebookOk = false;
                    return usr;
                }

                loginFacebookOk = false;
                conexao.getErrorStream();
                return  null;
            }
            catch(Exception e)
            {
                return null;
            }
        }

        @Override
        protected void onPostExecute(Usuario usuario)
        {
            LoginController loginController = new LoginController();
            if(usuario != null)
            {
                loginController.setAlunoLogado(usuario);
                loginFacebookOk = true;
            }
            else
                loginFacebookOk = false;

        }
    }
}
