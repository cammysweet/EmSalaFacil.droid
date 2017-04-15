package emsalafacil.emsalafacildroid.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Switch;
import android.widget.TextView;

import org.w3c.dom.Text;

import emsalafacil.emsalafacildroid.Controller.EnsalamentoController;
import emsalafacil.emsalafacildroid.Controller.LoginController;
import emsalafacil.emsalafacildroid.Model.Usuario;
import emsalafacil.emsalafacildroid.R;

public class EnsalamentoActivity extends AppCompatActivity {

    private TextView txtTurma;
    private TextView txtCurso;
    private TextView txtData;
    private TextView txtHoraInicio;
    private TextView txtHoraFim;
    private TextView txtDisciplina;
    private TextView txtProfessor;
    private Switch switchDisponibilidade;
    EnsalamentoController ensalamentoController = new EnsalamentoController();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ensalamento);

        txtTurma = (TextView) findViewById(R.id.textTurma);
        txtCurso = (TextView) findViewById(R.id.textCurso);
        txtData = (TextView) findViewById(R.id.textData);
        txtHoraInicio = (TextView) findViewById(R.id.textHoraInicio);
        txtHoraFim = (TextView) findViewById(R.id.textHoraFim);
        txtDisciplina = (TextView) findViewById(R.id.textAulaDesc);
        txtProfessor = (TextView) findViewById(R.id.textProfNome);
        switchDisponibilidade = (Switch) findViewById(R.id.switchDisponibilidade);

        //ensalamentoController.GetEnsalamento();

        //txtTurma.setText(txtTurma.getText()+aluno.getTurma().getDescricao());
    }
}
