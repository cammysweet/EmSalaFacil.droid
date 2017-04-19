package emsalafacil.emsalafacildroid.Controller;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;

import emsalafacil.emsalafacildroid.Model.*;
import emsalafacil.emsalafacildroid.R;
import emsalafacil.emsalafacildroid.Util;
import emsalafacil.emsalafacildroid.enumeradores.Disponibilidade;
import emsalafacil.emsalafacildroid.enumeradores.Turno;

/**
 * Created by camil on 10/04/2017.
 */

public class EnsalamentoController
{
    LoginController loginController = new LoginController();

    Usuario aluno;

    String urlApi = String.valueOf(R.string.urlApi);

    public Ensalamento GetEnsalamento(int dia, int mes, int ano)
    {
        Ensalamento ensalamentoRetorno;
        String retorno;

        try
        {
            aluno = loginController.getAlunoLogado();
            int idTurma = aluno.getTurma().getIdTurma();

            //***********************API*******************************************************************
//            URL apiEnd = new URL(urlApi + "/ensalamento/getEnsalamento/"+idTurma+"/"+dia+"/"+mes+"/"+ano);
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
//            ensalamentoRetorno = JsonToEnsalamento(retorno);
            //***********************FIM API**************************************************************

            //***********************FAKE*****************************************************************
            ensalamentoRetorno = GetEnsalamentoFake(dia, mes, ano);
            //***********************FIM FAKE*************************************************************

            return ensalamentoRetorno;
        }
        catch(Exception e)
        {
            return null;
        }
    }

    public Ensalamento JsonToEnsalamento(String json)
    {
        Ensalamento ensalamento;

        try
        {
            ensalamento = new Ensalamento();
            JSONObject mainObject = new JSONObject(json);
            Gson gson = new Gson();

            ensalamento.setIdEnsalamento(mainObject.getInt("idEnsalamento"));
            ensalamento.setTurno(Turno.valueOf(mainObject.getString("turno")));
            ensalamento.setDatainicio(Util.StringToDate(mainObject.getString("dataInicio")));
            ensalamento.setDataFim(Util.StringToDate(mainObject.getString("dataFim")));
            ensalamento.setDiaDaSemana(mainObject.getString("diaDaSemana"));
            ensalamento.setDisponibilidade(Disponibilidade.valueOf(mainObject.getString("disponibilidade")));
            ensalamento.setTurma(gson.fromJson(mainObject.getString("turma"), Turma.class));
            ensalamento.setDisciplina(gson.fromJson(mainObject.getString("disciplina"), Disciplina.class));
            ensalamento.setSala(gson.fromJson(mainObject.getString("sala"), Sala.class));

            return ensalamento;
        }
        catch (Exception e)
        {
            return  null;
        }
    }

    private Ensalamento GetEnsalamentoFake(int dia, int mes, int ano)
    {
        aluno = loginController.getAlunoLogado();
        SalaController salaContr = new SalaController();

        Ensalamento ensalamento = new Ensalamento();
        ensalamento.setIdEnsalamento(1);
        ensalamento.setTurno(Turno.NOTURNO);
        ensalamento.setDatainicio(Util.StringToDate(dia+"/"+mes+"/"+ano + "19:00"));
        ensalamento.setDataFim(Util.StringToDate(dia+"/"+mes+"/"+ano + "22:30"));
        ensalamento.setDisponibilidade(Disponibilidade.SIM);
        ensalamento.setTurma(aluno.getTurma());
        Disciplina disc = new Disciplina();
        disc.setCurso(aluno.getCurso());
        disc.setDescricaoDisciplina("Programação em componentes distribuídos");
        disc.setIdDisciplina(1);
        ensalamento.setDisciplina(disc);
        ensalamento.setSala(salaContr.GetSalaBy());

        return ensalamento;
    }
}
