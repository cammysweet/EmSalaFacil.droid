package emsalafacil.emsalafacildroid.Activities;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

import emsalafacil.emsalafacildroid.Controller.EnsalamentoController;
import emsalafacil.emsalafacildroid.Controller.LoginController;
import emsalafacil.emsalafacildroid.Model.Ensalamento;
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
    private Button btnShareFacebook;
    EnsalamentoController ensalamentoController = new EnsalamentoController();
    LoginController loginController = new LoginController();
    CalendarioActivity calendario = new CalendarioActivity();
    int dia = calendario.getDia();
    int mes = calendario.getMes();
    int ano = calendario.getAno();

    Ensalamento ensalamento = ensalamentoController.GetEnsalamento(dia, mes, ano);
    String disciplina = ensalamento.getDisciplina().getDescricaoDisciplina();
    String turma = ensalamento.getTurma().getDescricao();
    String sala = ensalamento.getSala().getDescricao();

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
        btnShareFacebook = (Button) findViewById(R.id.btnShareFacebook);


        Usuario aluno = loginController.getAlunoLogado();

        txtTurma.setText("Sua tchurma é: "+ turma);
        txtCurso.setText("Seu curso é: "+ aluno.getCurso().getNome());
        txtData.setText("Data: "+ dia+"/"+mes+"/"+ano);
        //horarioinicio
        //horariofim
        txtDisciplina.setText(disciplina);
        txtProfessor.setText("Diego");
                //arrumar inicio e fim, professor e disponibilidade
        // + ensalamento.getDisciplina());

        btnShareFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareContent(view);
            }
        });


    }

    public void shareContent(View view)
    {
                ShareLinkContent content = new ShareLinkContent.Builder()
                .setContentTitle("Em Sala Fácil")
                        .setContentDescription("No dia "+dia+"/"+mes+"/"+ano+" tenho aula de "+disciplina
                        +" com a turma "+turma+" na sala A103" )//+sala
                .setContentUrl(Uri.parse("https://developers.facebook.com"))
                .build();
        ShareDialog.show(EnsalamentoActivity.this, content);

    }
}
