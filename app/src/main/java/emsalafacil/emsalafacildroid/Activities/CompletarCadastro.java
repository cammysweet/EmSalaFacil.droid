package emsalafacil.emsalafacildroid.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import emsalafacil.emsalafacildroid.Controller.LoginController;
import emsalafacil.emsalafacildroid.R;

public class CompletarCadastro extends AppCompatActivity {

    private EditText editMatricula;
    private Button btnAvancarMatricula;
    private String facebookID;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completar_cadastro);

        editMatricula = (EditText) findViewById(R.id.editMatricula);
        btnAvancarMatricula = (Button) findViewById(R.id.btnAvancarMatricula);
        facebookID = getIntent().getStringExtra("FB_ID");

        Button btnAvancar = (Button) findViewById(R.id.btnAvancarMatricula);
        btnAvancar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String matricula = editMatricula.getText().toString();
                View focusView = null;
                boolean cancel = false;

                new LoginController().validateFacebookLogin(matricula);

                if (TextUtils.isEmpty(matricula))
                {
                    editMatricula.setError(getString(R.string.error_field_required));
                    focusView = editMatricula;
                    cancel = true;
                }
                if (cancel)
                {
                    // There was an error; don't attempt login and focus the first
                    // form field with an error.
                    focusView.requestFocus();
                }

                Intent intent = new Intent(CompletarCadastro.this, CalendarioActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        //método vazio, sobrescreve o método base e bloqueia o botão de voltar a tela
    }
}
