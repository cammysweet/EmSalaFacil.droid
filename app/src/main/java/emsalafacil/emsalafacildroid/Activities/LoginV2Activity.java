package emsalafacil.emsalafacildroid.Activities;

import android.content.Intent;
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
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;

import java.util.Arrays;

import emsalafacil.emsalafacildroid.Controller.AlunoController;
import emsalafacil.emsalafacildroid.Controller.LoginController;
import emsalafacil.emsalafacildroid.Model.LoginCommand;
import emsalafacil.emsalafacildroid.R;

public class LoginV2Activity extends AppCompatActivity {

    LoginController loginController = new LoginController();
    AlunoController alunoController = new AlunoController();
    CallbackManager callbackManager;
    EditText entryMatricula;
    EditText entrySenha;
    Button btnLogin;
    LoginButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
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

        /// login fb
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        callbackManager = CallbackManager.Factory.create();

        loginButton = (LoginButton) findViewById(R.id.btnLoginFacebookv2);

        loginButton.setReadPermissions(Arrays.asList("email","public_profile"));//, , "user_friends", "publish_actions"

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>()
        {
            @Override
            public void onSuccess(LoginResult loginResult)
            {
                //Profile profile = Profile.getCurrentProfile();
                //profile.
                GraphRequest.newMeRequest(
                        loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback()
                        {
                            @Override
                            public void onCompleted(JSONObject me, GraphResponse response)
                            {
                                if (response.getError() != null)
                                {
                                    // handle error
                                }
                                else
                                {
                                    String email = me.optString("email");
                                    String id = me.optString("id");
                                    // send email and id to your web server
                                    goMainScreen(id, email);
                                }
                            }
                        }).executeAsync();

                //Log.i("ID_FB",loginResult.getAccessToken().getUserId());

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


    private void goMainScreen(String idFacebook, String emailFacebook)
    {
        if(!loginController.loginFacebook(idFacebook))
        {
            Intent intent = new Intent(LoginV2Activity.this,CompletarCadastro.class);
            startActivity(intent);
        }

        Intent intent = new Intent(LoginV2Activity.this,CalendarioActivity.class);
        intent.putExtra("FB_ID",idFacebook);
        intent.putExtra("FB_EMAIL", emailFacebook);
        startActivity(intent);
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
        if (!TextUtils.isEmpty(password)) //TODO validar senha - && !loginController.isPasswordValid(password)
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
        else if(!loginController.loginNativo(loginObj))
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
}
