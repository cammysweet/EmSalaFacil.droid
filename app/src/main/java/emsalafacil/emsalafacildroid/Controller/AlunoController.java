package emsalafacil.emsalafacildroid.Controller;
import org.json.JSONObject;

import emsalafacil.emsalafacildroid.Model.Usuario;

/**
 * Created by etson on 10/04/2017.
 */

public class AlunoController
{

    public Usuario JsonToAluno(String json)
    {
        Usuario aluno;

        try
        {
            aluno = new Usuario();

            JSONObject mainObject = new JSONObject(json);
            aluno.setId(mainObject.getInt("Id"));
            aluno.setMatricula(mainObject.getString("Matricula"));
            aluno.setNome(mainObject.getString("Nome"));
            aluno.setEmail(mainObject.getString("Email"));
            aluno.setSenha(mainObject.getString("Senha"));
            aluno.setIdFacebook(mainObject.getString("IdFacebook"));
            aluno.setIdCurso(mainObject.getInt("IdCurso"));
            aluno.setIdTurma(mainObject.getInt("IdTurma"));
            aluno.setTipoUsuarioId(mainObject.getInt("TipoUsuarioId"));
            aluno.setCurso(new CursoController().JsonToCurso(mainObject.getString("Curso")));
            aluno.setTurma(new TurmaController().JsonToTurma(mainObject.getString("Turma")));

            return  aluno;
        }
        catch(Exception e)
        {
            return null;
        }
    }

}
