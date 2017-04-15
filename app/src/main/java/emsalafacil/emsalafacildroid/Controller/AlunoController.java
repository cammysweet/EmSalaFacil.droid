package emsalafacil.emsalafacildroid.Controller;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import emsalafacil.emsalafacildroid.Model.*;
import emsalafacil.emsalafacildroid.R;
import emsalafacil.emsalafacildroid.Util;
import emsalafacil.emsalafacildroid.enumeradores.Turno;

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

            /////////////////temporário/////////////////////////////////
            aluno = new Usuario();
            aluno.setNomeCompleto("Camila Cardoso");
            aluno.setEmail("camila@camila.com");

            Ensalamento ensalamento = new Ensalamento();
            //ensalamento.setDataFim("01/01/2017");
            //ensalamento.setDatainicio();
            ensalamento.setDiaDaSemana("Segunda");
            ensalamento.setDisponibilidade("ok");
            ensalamento.setIdEnsalamento(1);
            ensalamento.setTurno(Turno.NOTURNO);

            aluno.setEnsalamento(ensalamento);
            aluno.setMatricula(1201500669);

            Sala sala = new Sala();
            sala.setCapacidade(50);
            sala.setIdSala(1);

            aluno.setSala(sala);
            aluno.setSenha("1201500669");
            aluno.setStatus("ATIVO");

            Turma turma = new Turma();
            turma.setEnsalamento(ensalamento);
            //turma.setCurso(curso);
            turma.setDescricao("TDS 151 A");
            turma.setIdTurma(1);

            Curso curso = new Curso();
            curso.setId(1);
            curso.setmTurma(turma);
            curso.setNome("Análise e desenvolvimento de sistemas");
            turma.setCurso(curso);
            turma.setQuantidadeAlunos(50);
            aluno.setTurma(turma);
            /////////////////temporário/////////////////////////////////

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

            StringBuilder sb = new StringBuilder();
            sb.append("");
            sb.append(aluno.getMatricula());
            String matricula = sb.toString();

            aluno.setNomeCompleto(mainObject.getString("nomeAluno"));
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
