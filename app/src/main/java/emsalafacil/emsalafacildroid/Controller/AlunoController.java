package emsalafacil.emsalafacildroid.Controller;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import emsalafacil.emsalafacildroid.Model.Curso;
import emsalafacil.emsalafacildroid.Model.Turma;
import emsalafacil.emsalafacildroid.Model.Usuario;
import emsalafacil.emsalafacildroid.R;
import emsalafacil.emsalafacildroid.Util;
import emsalafacil.emsalafacildroid.enumeradores.Status;

/**
 * Created by camil on 10/04/2017.
 */

public class AlunoController
{
    String urlApi = String.valueOf(R.string.urlApi);

    public Usuario GetAlunoByIdFacebook(String idFacebook)
    {
        Usuario aluno;
        String retorno;

        try
        {
            URL apiEnd = new URL(urlApi + "/Usuario/RecuperarPorIdFacebook/"+idFacebook);
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
                is = conexao.getInputStream();
            else
                is = conexao.getErrorStream();

            retorno = Util.rawToJson(is);

            aluno = JsonToAluno(retorno);

            return aluno;
        }
        catch(Exception e)
        {
            aluno = GetAlunoFake();
            return aluno;
            //return null; //TODO quando existir usuário cadastrado na api, retornar somente o null
        }
    }

    public boolean CadastrarAluno(Usuario aluno)
    {
        String retorno;

        try
        {
            URL apiEnd = new URL(urlApi + "/Usuario/Cadastrar");
            int codigoResposta;
            HttpURLConnection conexao;

            conexao = (HttpURLConnection) apiEnd.openConnection();
            conexao.setRequestMethod("POST");
            //TODO CONFIRMAR ONDE POE O OBJ DO POST
            conexao.setReadTimeout(15000);
            conexao.setConnectTimeout(15000);
            conexao.connect(); //InvocationTargetException

            codigoResposta = conexao.getResponseCode();
            if (codigoResposta < HttpURLConnection.HTTP_BAD_REQUEST)
                return false;

            return true;
        }
        catch(Exception e)
        {
            aluno = GetAlunoFake();
            return true;
            //return false; //TODO quando existir usuário cadastrado na api, retornar somente o false
        }
    }


    public Usuario GetAlunoByMatricula(String matricula)
    {
        Usuario aluno;
        String retorno;

        try
        {
            URL apiEnd = new URL(urlApi + "/Usuario/Recuperar/"+matricula);
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
                is = conexao.getInputStream();
            else
                is = conexao.getErrorStream();

            retorno = Util.rawToJson(is);

            aluno = JsonToAluno(retorno);

            return aluno;
        }
        catch(Exception e)
        {
            aluno = GetAlunoFake();
            return aluno;
            //return null; //TODO quando existir usuário cadastrado na api, retornar somente o null
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

        Turma turma = new TurmaController().GetTurmaByMatricula(aluno.getMatricula());
        aluno.setTurma(turma);

        Curso curso = new CursoController().GetCursoByMatricula(aluno.getMatricula());
        aluno.setCurso(curso);

        aluno.setStatus(Status.ATIVO);

        return  aluno;
    }

}
