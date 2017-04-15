package emsalafacil.emsalafacildroid.Controller;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import emsalafacil.emsalafacildroid.Model.*;
import emsalafacil.emsalafacildroid.R;
import emsalafacil.emsalafacildroid.Util;
import emsalafacil.emsalafacildroid.enumeradores.Status;
import emsalafacil.emsalafacildroid.enumeradores.TipoUsuario;

/**
 * Created by camil on 10/04/2017.
 */

public class AlunoController
{
    String urlApi = String.valueOf(R.string.urlApi);

    public Usuario GetAlunoByMatricula(String matricula)
    {
        Usuario aluno;
        String retorno;

        try
        {
            //******************************API**************************************
//            URL apiEnd = new URL(urlApi + "/usuario/getUsuario/"+matricula);
//            int codigoResposta;
//            HttpURLConnection conexao;
//            InputStream is;
//
//            conexao = (HttpURLConnection) apiEnd.openConnection();
//            conexao.setRequestMethod("GET");
//            conexao.setReadTimeout(15000);
//            conexao.setConnectTimeout(15000);
//            conexao.connect(); //InvocationTargetException
//
//            codigoResposta = conexao.getResponseCode();
//            if (codigoResposta < HttpURLConnection.HTTP_BAD_REQUEST)
//                is = conexao.getInputStream();
//            else
//                is = conexao.getErrorStream();
//
//            retorno = Util.rawToJson(is);
//
//            aluno = JsonToAluno(retorno);
//******************************FIM API**************************************

            //FAKE
            aluno = GetAlunoFake();
            //FIM FAKE

            return aluno;
        }
        catch(Exception e)
        {
            return null;
        }
    }

    private Usuario JsonToAluno(String json)
    {
        Usuario aluno;

        try
        {
            aluno = new Usuario();

            JSONObject mainObject = new JSONObject(json);
            aluno.setMatricula(mainObject.getInt("matricula"));
            aluno.setNome(mainObject.getString("nomeAluno"));
            aluno.setEmail(mainObject.getString("email"));
            aluno.setSenha(mainObject.getString("senha"));

            Turma turma = new TurmaController().GetTurmaByMatricula(aluno.getMatricula());
            aluno.setTurma(turma);

            Curso curso = new CursoController().GetCursoByMatricula(aluno.getMatricula());
            aluno.setCurso(curso);

            aluno.setStatus(Status.valueOf(mainObject.getString("status")));
            aluno.setTipoUsuario(TipoUsuario.valueOf(mainObject.getString("tipoUsuario")));

            return  aluno;
        }
        catch(Exception e)
        {
            return null;
        }
    }

    public Usuario GetAlunoFake()
    {
        Usuario aluno = new Usuario();

        aluno.setMatricula(1201500669);
        aluno.setNome("Camila Cardoso");
        aluno.setEmail("camila@camila.com");
        aluno.setSenha("1201500669");

        Turma turma = new TurmaController().GetTurmaFake();
        aluno.setTurma(turma);

        Curso curso = new CursoController().GetCursoFake();
        aluno.setCurso(curso);

        aluno.setStatus(Status.ATIVO);
        aluno.setTipoUsuario(TipoUsuario.ALUNO);

        return  aluno;
    }

    public List<Usuario> getAlunosByTurma(int idTurma)
    {
        //TODO implementar método getAlunos bu idTurma
        return null;
    }

    public List<Usuario> getAlunosByCurso(int idCurso)
    {
        //TODO implementar método getAlunos bu idCurso
        return null;
    }
}
