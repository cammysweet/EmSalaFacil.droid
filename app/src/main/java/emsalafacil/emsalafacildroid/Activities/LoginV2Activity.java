package emsalafacil.emsalafacildroid.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
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

import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

import emsalafacil.emsalafacildroid.Controller.LoginController;
import emsalafacil.emsalafacildroid.Model.Autenticacao;
import emsalafacil.emsalafacildroid.Model.LoginCommand;
import emsalafacil.emsalafacildroid.Model.Usuario;
import emsalafacil.emsalafacildroid.R;
import emsalafacil.emsalafacildroid.Util;

public class LoginV2Activity extends AppCompatActivity {

    LoginController loginController = new LoginController();
    CallbackManager callbackManager;
    EditText entryMatricula;
    EditText entrySenha;
    Button btnLogin;
    LoginButton loginButton;
    Boolean loginFacebookApiOk;
    Boolean loginNativoApiOk;
    String statusCodeLoginFacebookApi;
    String statusCodeLoginNativoApi;
    String jsonUsuarioApi;
    String erroLoginFacebookApi;
    Button botaoFacebook;

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
        botaoFacebook = (Button) findViewById(R.id.buttonFacebook);
        botaoFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginFacebook();
            }
        });
//        AccessToken accessToken = AccessToken.getCurrentAccessToken();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void goMainScreen(String idFacebook, String emailFacebook)
    {
        if(!loginFacebook(idFacebook))
        {
            if(statusCodeLoginFacebookApi == "401")
            {
                Intent intent = new Intent(LoginV2Activity.this,CompletarCadastro.class);
                intent.putExtra("FB_ID", idFacebook);
                intent.putExtra("FB_EMAIL", emailFacebook);
                startActivity(intent);
            }
            else
            {
                LoginController.logout();
                Toast.makeText(getApplicationContext(), "Oops! Algo deu errado. Tente novamente. Resposta do servidor: "
                        + statusCodeLoginFacebookApi,
                        Toast.LENGTH_LONG).show();
            }

        }
        else
        {
            if(Autenticacao.getUsuarioLogado() != null)
            {
                Intent intent = new Intent(LoginV2Activity.this, CalendarioActivity.class);
                startActivity(intent);
            }
            else
            {
                LoginController.logout();

                if(statusCodeLoginFacebookApi == null) statusCodeLoginFacebookApi = "sem resposta";
                Toast.makeText(getApplicationContext(), "Oops! Usuário não carregado. Tente novamente. Resposta do servidor: "
                        + statusCodeLoginFacebookApi,
                        Toast.LENGTH_LONG).show();
            }

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
            if(statusCodeLoginNativoApi == "401")
            {
                entryMatricula.setError("Usuário não cadastrado nesta combinação de matrícula e senha. Verifique.");
                focusView = entryMatricula;
                cancel = true;
            }
            else
            {
                if(statusCodeLoginNativoApi == null) statusCodeLoginNativoApi = "sem resposta";
                Toast.makeText(getApplicationContext(), "Oops! Algo deu errado, tente novamente. Resposta do servidor: "
                        + statusCodeLoginNativoApi, Toast.LENGTH_LONG).show();
                focusView = entryMatricula;
                cancel = true;
            }

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

    public  boolean loginNativo(LoginCommand login)
    {
        try
        {
            new LoginNativoApi().execute(login);
            Thread.sleep(5000);

            if (!loginNativoApiOk)
                return false;

            if(Autenticacao.getUsuarioLogado() != null)
            {
                Autenticacao.setLogado(true);
                Autenticacao.setLogadoFacebook(false);
                Autenticacao.setUsuarioLogado(Autenticacao.getUsuarioLogado());
                return  true;
            }
            else
            {
                if(jsonUsuarioApi != null && jsonUsuarioApi != "")
                {
                    Autenticacao.setUsuarioLogado(Util.JsonToUsuario(jsonUsuarioApi));
                    Autenticacao.setLogado(true);
                    Autenticacao.setLogadoFacebook(false);
                    return true;
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Oops! Usuário não carregado, tente novamente.  ",
                            Toast.LENGTH_SHORT).show();
                    return  false;
                }
            }
        }
       catch(Exception e)
       {
           Toast.makeText(getApplicationContext(), "Oops! Erro interno, tente novamente.  "
                           + e.getMessage(),
                   Toast.LENGTH_LONG).show();
           return false;
       }
    }

    public boolean loginFacebook(String idFacebook)
    {
        try
        {
            new LoginFacebookApi().execute(idFacebook);
            Thread.sleep(5000);
            if(!loginFacebookApiOk)
                return false;
            if(Autenticacao.getUsuarioLogado() != null)
            {
                Autenticacao.setLogadoFacebook(true);
                Autenticacao.setLogado(true);
                Autenticacao.setUsuarioLogado(Autenticacao.getUsuarioLogado());
                return true;
            }
            else
            {
                if(jsonUsuarioApi != null && jsonUsuarioApi != "")
                {
                    Autenticacao.setUsuarioLogado(Util.JsonToUsuario(jsonUsuarioApi));
                    Autenticacao.setLogado(true);
                    Autenticacao.setLogadoFacebook(false);
                    return true;
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Oops! Usuário não carregado, tente novamente.  ",
                            Toast.LENGTH_SHORT).show();
                    return  false;
                }
            }
        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), "Oops! Erro interno, tente novamente.  "
                            + e.getMessage(),
                    Toast.LENGTH_LONG).show();
            return false;
        }
    }

    private class LoginNativoApi extends AsyncTask<LoginCommand, Void, Usuario>
    {
        @Override
        protected Usuario doInBackground(LoginCommand... params)
        {
            Gson gson = new Gson();
            int serverResponseCode;
            String serverResponseMessage = "";
            HttpURLConnection urlConnection = null;
            try
            {
                URL url = new URL("http://caiofelipe.com/api/usuario/login");
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setRequestProperty("Content-type", "application/json");
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);

                DataOutputStream outputStream = new DataOutputStream(urlConnection.getOutputStream());

                String result = gson.toJson(params[0]);
                outputStream.writeBytes(result);

                serverResponseCode = urlConnection.getResponseCode();

                if(serverResponseCode == HttpURLConnection.HTTP_OK)
                {
                    loginNativoApiOk = true;
                    statusCodeLoginNativoApi = "200";
                    serverResponseMessage = Util.webToString(urlConnection.getInputStream());
                }
                else if(serverResponseCode == HttpURLConnection.HTTP_UNAUTHORIZED)
                {
                    statusCodeLoginNativoApi = "401";
                    loginNativoApiOk = false;
                }

                else
                {
                    statusCodeLoginNativoApi = urlConnection.getErrorStream().toString();
                    loginFacebookApiOk = false;
                }

                outputStream.flush();
                outputStream.close();

                if(serverResponseMessage != "")
                {
                    jsonUsuarioApi = serverResponseMessage;
                    return Util.JsonToUsuario(serverResponseMessage);
                }


                return null;
            }
            catch(Exception e)
            {
                statusCodeLoginNativoApi = e.getMessage();
                loginNativoApiOk = false;
                return null;
            }
        }

        @Override
        protected void onPostExecute(Usuario usuario)
        {
            Autenticacao.setUsuarioLogado(usuario);
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
                if (codigoResposta == HttpURLConnection.HTTP_OK)
                {
                    loginFacebookApiOk = true;
                    statusCodeLoginFacebookApi = "200";
                    is = conexao.getInputStream();
                    String json = Util.rawToJson(is);
                    jsonUsuarioApi = json;
                    return Util.JsonToUsuario(json);
                }

                else
                {
                    if(codigoResposta == HttpURLConnection.HTTP_UNAUTHORIZED)
                        statusCodeLoginFacebookApi = "401";
                    else
                    {
                        statusCodeLoginFacebookApi = conexao.getErrorStream().toString();
                    }

                    loginFacebookApiOk = false;
                    return null;
                }
            }
            catch(Exception e)
            {
                statusCodeLoginFacebookApi = e.getMessage();
                loginFacebookApiOk = false;
                return null;
            }
        }

        @Override
        protected void onPostExecute(Usuario usuario)
        {
            if(usuario != null)
            {
                Autenticacao.setUsuarioLogado(usuario);
            }

        }
    }
    private void loginFacebook (){
        loginButton.performClick();
        loginButton.setReadPermissions(Arrays.asList("email","public_profile"));//, , "user_friends", "publish_actions"
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>()
        {
            @Override
            public void onSuccess(LoginResult loginResult)
            {
                Log.i("ID_FB",loginResult.getAccessToken().getUserId());
                goMainScreen(loginResult.getAccessToken().getUserId(),
                        "camila.cardoso65@hotmail.com");

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
    }

}
