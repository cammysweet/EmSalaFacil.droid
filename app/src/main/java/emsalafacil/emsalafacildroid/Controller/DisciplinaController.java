package emsalafacil.emsalafacildroid.Controller;

import java.util.ArrayList;
import java.util.List;

import emsalafacil.emsalafacildroid.Model.Disciplina;

/**
 * Created by natha on 15/04/2017.
 */

public class DisciplinaController
{
//    public Disciplina getDisciplinaFake()
//    {
//        Disciplina disc = new Disciplina(1, "Análise e desenvolvimento de sistemas", null
//        ,)
//    }

    public Disciplina JsonToDisciplina(String json)
    {
        //TODO implementar método
        return null;
    }

    public List<Disciplina> GetDisciplinasByCurso(int idCurso)
    {
        //TODO implementar método
        return null;
    }

    public List<Disciplina> GetDisciplinasByTurma(int idTurma)
    {
        Disciplina disciplina;
        String retorno;

        try
        {
            //******************************API**************************************
//            URL apiEnd = new URL(urlApi + "/disciplina/getdisciplinabyturma/"+idTurma);
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
//            disciplinas = JsonToDisciplina(retorno);  TODO implementar deserialização de lista
//******************************FIM API**************************************

            //FAKE
            disciplina = GetDisciplinaFake();
            List<Disciplina> lista = new ArrayList<Disciplina>();
            lista.add(disciplina);
            //FIM FAKE

            return lista;
        }
        catch(Exception e)
        {
            return null;
        }
    }

    public Disciplina GetDisciplinaFake()
    {
        CursoController cursoController = new CursoController();
        Disciplina disc = new Disciplina(1, "Programação de componentes distribuídos",
                cursoController.GetCursoFake());
        return disc;
    }
}
