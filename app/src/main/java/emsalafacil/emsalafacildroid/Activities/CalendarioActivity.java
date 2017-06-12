package emsalafacil.emsalafacildroid.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.google.gson.Gson;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import emsalafacil.emsalafacildroid.Controller.LoginController;
import emsalafacil.emsalafacildroid.Model.Autenticacao;
import emsalafacil.emsalafacildroid.Model.Ensalamento;
import emsalafacil.emsalafacildroid.Model.EnsalamentoCommand;
import emsalafacil.emsalafacildroid.Model.Usuario;
import emsalafacil.emsalafacildroid.R;
import emsalafacil.emsalafacildroid.Util;

public class CalendarioActivity extends AppCompatActivity
{
    LoginController loginController = new LoginController();


    private TextView lblNome;
    private TextView lblMatricula;
    private TextView lblTurma;
    private TextView lblCurso;
    CalendarView simpleCalendarView;
    private static int dia;
    private static int mes;
    private static int ano;
    private static Ensalamento ensalamento;

    public static int getDia() { return dia; }
    public static int getMes() { return mes; }
    public static int getAno() { return ano; }
    public static Ensalamento getEnsalamento() { return ensalamento; }


    @Override
    public void onBackPressed() {
        //método vazio, sobrescreve o método base e bloqueia o botão de voltar a tela
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);

        final Context packageContext = this;

        if (AccessToken.getCurrentAccessToken() == null)
        {
            Toast.makeText(getApplicationContext(), "Usuário Logado", Toast.LENGTH_SHORT).show();
        }

        lblNome = (TextView) findViewById(R.id._lblBoasVindas);
        lblMatricula = (TextView) findViewById(R.id._lblMatricula);
        lblTurma = (TextView) findViewById(R.id._lblTurma);
        lblCurso = (TextView) findViewById(R.id._lblCurso);

        final Usuario aluno = Autenticacao.getUsuarioLogado();

        lblNome.setText("Bem Vindo (a) "+aluno.getNome());
        lblMatricula.setText("Matrícula: "+aluno.getMatricula());
        lblTurma.setText("Sua turma é: "+aluno.getTurma().getDescricao());
        lblCurso.setText("Curso: "+aluno.getCurso().getNome());
        simpleCalendarView = (CalendarView) findViewById(R.id.calendarView);
        simpleCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

                try
                {
                    // display the selected date by using a toast
                    //Toast.makeText(getApplicationContext(), dayOfMonth +
                    //"/" + month + "/" + year, Toast.LENGTH_LONG).show();

                    dia = dayOfMonth;
                    mes = month+1;
                    ano = year;

                    EnsalamentoCommand cmd = new EnsalamentoCommand();
                    cmd.setIdTurma(aluno.getIdTurma());
                    cmd.setAno(ano);
                    cmd.setDia(dia);
                    cmd.setMes(mes);

                    new EnsalamentoApi().execute(cmd);
                    Thread.sleep(5000);

                    if(ensalamento != null)
                    {
                        if(ensalamento.getId() != 0)
                        {
                            Intent ensalamentoView = new Intent(packageContext, EnsalamentoActivity.class);
                            startActivity(ensalamentoView);
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "Não existe ensalamento cadastrado para sua turma nesta data.",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Oops... Algo deu errado, " +
                                "tente novamente.", Toast.LENGTH_LONG).show();
                    }

                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(), "Erro interno " +
                            e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private class EnsalamentoApi extends AsyncTask<EnsalamentoCommand, Void, Ensalamento>
    {
        @Override
        protected Ensalamento doInBackground(EnsalamentoCommand... params)
        {
            Gson gson = new Gson();
            int serverResponseCode;
            String serverResponseMessage = "";
            HttpURLConnection urlConnection = null;
            try
            {
                URL url = new URL("http://caiofelipe.com/api/ensalamento/getbydataeturma");
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
                    serverResponseMessage = Util.webToString(urlConnection.getInputStream());
                    ensalamento = Util.JsonToEnsalamento(serverResponseMessage);
                }
                else
                {
                    ensalamento = new Ensalamento();

                }
                outputStream.flush();
                outputStream.close();

                return ensalamento;
            }
            catch(Exception e)
            {
                return null;
            }
        }

        @Override
        protected void onPostExecute(Ensalamento ens)
        {
            ensalamento = ens;
        }
    }

}
