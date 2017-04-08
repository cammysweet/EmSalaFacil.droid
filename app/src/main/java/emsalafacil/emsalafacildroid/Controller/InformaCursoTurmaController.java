package emsalafacil.emsalafacildroid.Controller;

import android.content.Intent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import emsalafacil.emsalafacildroid.Activities.InformaCursoTurmaActivity;
import emsalafacil.emsalafacildroid.Model.Curso;

/**
 * Created by camil on 08/04/2017.
 */

public class InformaCursoTurmaController
{
    public List<Curso> PreencheSpinnerCursos()
    {
        //TODO adicionar lógica que pega cursos da API quando possível

        List<Curso> cursos = new ArrayList<Curso>();
        Curso curso1 = new Curso(1, "Análise e Desenvolvimento de Sistemas");
        Curso curso2 = new Curso(2, "Marketing");
        Curso curso3 = new Curso(3, "Estética");
        cursos.add(curso1);
        cursos.add(curso2);
        cursos.add(curso3);
        return cursos;
    }


}
