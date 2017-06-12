package emsalafacil.emsalafacildroid.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import emsalafacil.emsalafacildroid.Model.Autenticacao;
import emsalafacil.emsalafacildroid.Model.Usuario;
import emsalafacil.emsalafacildroid.Model.VinculoFacebookCommand;
import emsalafacil.emsalafacildroid.R;
import emsalafacil.emsalafacildroid.Util;

/***
 * Vincula um usuário com um id do facebook validando se o email do facebook é o mesmo do cadastro.
 * **/
public class CompletarCadastro extends AppCompatActivity {

    private EditText editMatricula;
    private Button btnAvancarMatricula;
    private String facebookID;
    private String facebookEmail;
    private Boolean vinculadoOk;
    private String jsonUsuarioApi;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completar_cadastro);

        editMatricula = (EditText) findViewById(R.id.editMatricula);
        btnAvancarMatricula = (Button) findViewById(R.id.btnAvancarMatricula);
        facebookID = getIntent().getStringExtra("FB_ID");
        facebookEmail = getIntent().getStringExtra("FB_EMAIL");

        btnAvancarMatricula.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try
                {
                    String matricula = editMatricula.getText().toString();
                    View focusView = null;
                    boolean cancel = false;

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

                    VinculoFacebookCommand cmd = new VinculoFacebookCommand();
                    cmd.setMatricula(matricula);
                    cmd.setIdFacebook(facebookID);
                    cmd.setEmailFacebook(facebookEmail);

                    new VinculaUsuarioFacebookApi().execute(cmd);
                    Thread.sleep(5000);
                    int x = 0;
                    if(vinculadoOk)
                    {
                        new RecuperaUsuarioApi().execute(matricula);
                        Thread.sleep(5000);
                        if(Autenticacao.getUsuarioLogado() != null)
                        {
                            Autenticacao.setVinculadoFacebook(true);
                            Intent intent = new Intent(CompletarCadastro.this, CalendarioActivity.class);
                            startActivity(intent);
                        }
                        else
                        {
                            if(jsonUsuarioApi != "" && jsonUsuarioApi != null)
                            {
                                Autenticacao.setUsuarioLogado(Util.JsonToUsuario(jsonUsuarioApi));
                                Autenticacao.setVinculadoFacebook(true);
                                Intent intent = new Intent(CompletarCadastro.this, CalendarioActivity.class);
                                startActivity(intent);
                            }
                            else
                                Toast.makeText(getApplicationContext(), "Erro ao  recuperar usuário.",
                                        Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                        Toast.makeText(getApplicationContext(), "Erro ao vincular contas.", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(), "Erro interno: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        //método vazio, sobrescreve o método base e bloqueia o botão de voltar a tela
    }

    private class VinculaUsuarioFacebookApi extends  AsyncTask<VinculoFacebookCommand, Void, Boolean>
    {
        @Override
        protected Boolean doInBackground(VinculoFacebookCommand... params)
        {
            Gson gson = new Gson();
            HttpURLConnection urlConnection = null;
            try
            {
                URL url = new URL("http://caiofelipe.com/api/usuario/vincularfacebook");
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setRequestProperty("Content-type", "application/json");
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);

                DataOutputStream outputStream = new DataOutputStream(urlConnection.getOutputStream());

                String result = gson.toJson(params[0]);
                outputStream.writeBytes(result);

                int serverResponseCode = urlConnection.getResponseCode();
                outputStream.flush();
                outputStream.close();
                if(serverResponseCode == HttpURLConnection.HTTP_OK)
                {
                    vinculadoOk = true;
                    return  true;
                }
                else
                {
                    vinculadoOk = false;
                    return  false;
                }
            }
            catch(Exception e)
            {
                vinculadoOk = false;
                return  false;
            }
            finally
            {

                if (urlConnection != null)
                    urlConnection.disconnect();
            }
        }

        @Override
        protected void onPostExecute(Boolean vinculado)
        {
            if(vinculado)
            {
                Autenticacao.setVinculadoFacebook(true);
                vinculadoOk = true;
            }
            else
                vinculadoOk = false;
        }
    }

    private class RecuperaUsuarioApi extends AsyncTask<String, Void, Usuario>
    {
        @Override
        protected Usuario doInBackground(String... params) {
            try
            {
                String urlApi = "http://caiofelipe.com/api/"; // String.valueOf(R.string.urlApi);

                URL apiEnd = new URL(urlApi + "usuario/getbymatricula/"+params[0]);
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
                    String json = Util.rawToJson(is);
                    jsonUsuarioApi = json;
                    return Util.JsonToUsuario(json);
                }
                else
                {
                    jsonUsuarioApi = "";
                    return  new Usuario();
                }
            }
            catch(Exception e)
            {
                jsonUsuarioApi = "";
                return  new Usuario();
            }
        }

        @Override
        protected void onPostExecute(Usuario usuario)
        {
            if(usuario == null) usuario = new Usuario();
            Autenticacao.setUsuarioLogado(usuario);
        }
    }
}
