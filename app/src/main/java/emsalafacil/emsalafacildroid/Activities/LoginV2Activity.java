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
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

import emsalafacil.emsalafacildroid.Controller.AlunoController;
import emsalafacil.emsalafacildroid.Controller.LoginController;
import emsalafacil.emsalafacildroid.Model.Usuario;
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
                attemptLogin();
            }
        });

        /// login fb
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        callbackManager = CallbackManager.Factory.create();

        loginButton = (LoginButton) findViewById(R.id.btnLoginFacebookv2);

        loginButton.setReadPermissions(Arrays.asList("email", "public_profile", "user_friends"));

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>()
        {
            @Override
            public void onSuccess(LoginResult loginResult)
            {
                String idFacebook = loginResult.getAccessToken().getUserId();

                Profile profile = Profile.getCurrentProfile();

                loginResult.getRecentlyGrantedPermissions();
                Log.i("ID_FB",idFacebook);
                if(loginController.isLoginFacebookOk(idFacebook))
                    goMainScreen();

                Usuario aluno = new Usuario();
                aluno.setSenha("");
                aluno.setEmail("");
                aluno.setNome(profile.getFirstName() + profile.getMiddleName() + profile.getLastName());
                aluno.setIdFacebook(idFacebook);

                alunoController.CadastrarAluno(aluno);
                goMainScreen();
            }

            @Override
            public void onCancel() {
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


    private void goMainScreen()
    {
        Intent intent = new Intent(LoginV2Activity.this,CompletarCadastro.class);
        startActivity(intent);
    }


    private void attemptLogin()
    {
        // Reset errors.
        entryMatricula.setError(null);
        entrySenha.setError(null);

        // Store values at the time of the login attempt.
        String matricula = entryMatricula.getText().toString();
        String password = entrySenha.getText().toString();

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
        else if(!loginController.isLoginApiOk(matricula, password))
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
            Intent intent = new Intent(this, CompletarCadastro.class);
            startActivity(intent);
        }
    }
}
