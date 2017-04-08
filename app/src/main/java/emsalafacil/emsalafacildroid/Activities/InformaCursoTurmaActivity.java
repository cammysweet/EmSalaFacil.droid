package emsalafacil.emsalafacildroid.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import emsalafacil.emsalafacildroid.Controller.InformaCursoTurmaController;
import emsalafacil.emsalafacildroid.Model.Curso;
import emsalafacil.emsalafacildroid.R;

public class InformaCursoTurmaActivity extends AppCompatActivity {

    InformaCursoTurmaController _controller = new InformaCursoTurmaController();
    private Spinner spn1;
    private String nomeCurso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informa_curso_turma);
//

        List<Curso> listaCursos = _controller.PreencheSpinnerCursos();
        List<String> listaNomeCursos = new ArrayList<String>();
        for (Curso curso:listaCursos)
        {
            listaNomeCursos.add(curso.getNome());
        }

        //Identifica o Spinner no layout
        spn1 = (Spinner) findViewById(R.id.spinnerCurso);
        //Cria um ArrayAdapter usando um padrão de layout da classe R do android, passando o ArrayList nomes
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, listaNomeCursos);
        ArrayAdapter<String> spinnerArrayAdapter = arrayAdapter;
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spn1.setAdapter(spinnerArrayAdapter);

        //Método do Spinner para capturar o item selecionado
        spn1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int posicao, long id) {
                //pega nome pela posição
                nomeCurso = parent.getItemAtPosition(posicao).toString();

                //imprime um Toast na tela com o nome que foi selecionado
                //Toast.makeText(ExemploSpinner.this, "Nome Selecionado: " + nomeCurso, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void OnClickAvancar(View v)
    {
        Intent calendario = new Intent(this, CalendarioActivity.class);
        startActivity(calendario);
    }

}
