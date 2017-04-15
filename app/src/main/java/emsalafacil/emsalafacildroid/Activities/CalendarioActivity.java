package emsalafacil.emsalafacildroid.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CalendarView;
import android.widget.TextView;

import emsalafacil.emsalafacildroid.Controller.*;
import emsalafacil.emsalafacildroid.Model.*;
import emsalafacil.emsalafacildroid.R;

public class CalendarioActivity extends AppCompatActivity
{
    LoginController loginController = new LoginController();
    EnsalamentoController ensalamentoController = new EnsalamentoController();

    private TextView lblNome;
    private TextView lblMatricula;
    private TextView lblTurma;
    private TextView lblCurso;
    CalendarView simpleCalendarView;
    private boolean dayChanged = false;

    private static int dia;
    private static int mes;
    private static int ano;

    public static int getDia() { return dia; }
    public static int getMes() { return mes; }
    public static int getAno() { return ano; }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);

        lblNome = (TextView) findViewById(R.id._lblBoasVindas);
        lblMatricula = (TextView) findViewById(R.id._lblMatricula);
        lblTurma = (TextView) findViewById(R.id._lblTurma);
        lblCurso = (TextView) findViewById(R.id._lblCurso);

        final Usuario aluno = loginController.getAlunoLogado();

        lblNome.setText("Bem Vindo (a) "+aluno.getNome());
        lblMatricula.setText("Matrícula: "+aluno.getMatricula());
        lblTurma.setText("Sua turma é: "+aluno.getTurma().getDescricao());
        lblCurso.setText("Curso: "+aluno.getCurso().getNome());

        simpleCalendarView = (CalendarView) findViewById(R.id.calendarView);
        simpleCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                // display the selected date by using a toast
                //Toast.makeText(getApplicationContext(), dayOfMonth +
                        //"/" + month + "/" + year, Toast.LENGTH_LONG).show();

                dia = dayOfMonth;
                mes = month+1;
                ano = year;
                dayChanged = true;
            }
        });

        if(dayChanged)
        {
            Intent ensalamentoView = new Intent(this, EnsalamentoActivity.class);
            startActivity(ensalamentoView);
        }
    }

}
