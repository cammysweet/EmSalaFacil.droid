package emsalafacil.emsalafacildroid.Controller;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import emsalafacil.emsalafacildroid.Model.Aluno;
import emsalafacil.emsalafacildroid.Model.Curso;
import emsalafacil.emsalafacildroid.Model.Ensalamento;
import emsalafacil.emsalafacildroid.Model.Turma;
import emsalafacil.emsalafacildroid.R;
import emsalafacil.emsalafacildroid.Util;

/**
 * Created by camil on 10/04/2017.
 */

public class AlunoController
{
    String urlApi = String.valueOf(R.string.urlApi);

    public Aluno GetAlunoByMatricula(String matricula)
    {
        Aluno aluno;
        String retorno;

        try
        {
            URL apiEnd = new URL(urlApi + "/aluno/getAluno/"+matricula);
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
            return null;
        }
    }

    private Aluno JsonToAluno(String json)
    {
        Aluno aluno;

        try
        {
            aluno = new Aluno();

            JSONObject mainObject = new JSONObject(json);
            aluno.setMatricula(mainObject.getInt("matricula"));

            StringBuilder sb = new StringBuilder();
            sb.append("");
            sb.append(aluno.getMatricula());
            String matricula = sb.toString();

            aluno.setNomeAluno(mainObject.getString("nomeAluno"));
            aluno.setStatus(mainObject.getString("status"));
            String ensalamentoJson = mainObject.getString("ensalamento");
            Ensalamento ensalamento = new EnsalamentoController().JsonToEnsalamento(ensalamentoJson);

            Turma turma = new TurmaController().GetTurmaByMatricula(matricula);
            Curso curso = new CursoController().GetCursoByMatricula(matricula);
            aluno.setEnsalamento(ensalamento);

            return  aluno;
        }
        catch(Exception e)
        {
            return null;
        }
    }
}
