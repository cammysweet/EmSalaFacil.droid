package emsalafacil.emsalafacildroid.Controller;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import emsalafacil.emsalafacildroid.Model.Usuario;
import emsalafacil.emsalafacildroid.Model.VinculoFacebookCommand;

/**
 * Created by camil on 10/04/2017.
 */

public class AlunoController
{
    String urlApi = "http://caiofelipe.com/api/"; // String.valueOf(R.string.urlApi);

    Gson gson = new Gson();

    public Boolean VincularFacebook(VinculoFacebookCommand cmd)
    {
        try
        {
            URL apiEnd = new URL(urlApi + "usuario/vincularfacebook/");
            int codigoResposta;
            HttpURLConnection conexao;
            InputStream is;

            conexao = (HttpURLConnection) apiEnd.openConnection();
            conexao.setRequestMethod("POST");
            conexao.setReadTimeout(15000);
            conexao.setConnectTimeout(15000);
            conexao.connect(); //InvocationTargetException

            conexao.setDoInput(true);
            conexao.setDoOutput(true);

            OutputStreamWriter writer = new OutputStreamWriter(conexao.getOutputStream());
            writer.write(gson.toJson(cmd));

            codigoResposta = conexao.getResponseCode();
            if (codigoResposta < HttpURLConnection.HTTP_BAD_REQUEST)
                return true;

            return false;
        }
        catch(Exception e)
        {
            return false;
        }
    }

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
            return  aluno;
        }
        catch(Exception e)
        {
            return null;
        }
    }

//    public Usuario GetAlunoFake()
//    {
//        Usuario aluno = new Usuario();
//
//        aluno.setMatricula(1201500669);
//        aluno.setNome("Camila Cardoso");
//        aluno.setEmail("camila@camila.com");
//        aluno.setSenha("1201500669");
//        aluno.setIdFacebook("akhlsuiahouirhiuejxoiau298123875862");
//        Turma turma = new TurmaController().GetTurmaByMatricula(aluno.getMatricula());
//        aluno.setTurma(turma);
//
//        Curso curso = new CursoController().GetCursoByMatricula(aluno.getMatricula());
//        aluno.setCurso(curso);
//
//        aluno.setStatus(Status.ATIVO);
//
//        return  aluno;
//    }

}
