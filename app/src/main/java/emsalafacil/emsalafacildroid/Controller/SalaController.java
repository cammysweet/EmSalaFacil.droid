package emsalafacil.emsalafacildroid.Controller;

import org.json.JSONObject;

import emsalafacil.emsalafacildroid.Model.Sala;
import emsalafacil.emsalafacildroid.enumeradores.TipoSala;

/**
 * Created by camil on 15/04/2017.
 */

public class SalaController
{
    public  Sala GetSalaBy()
    {
        Sala sala;

        try
        {
            //***************************************************************
//            URL apiEnd = new URL(urlApi + "/sala/getsala/"+matricula);
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
//            turma = JsonToTurma(retorno);
            //***************************************************************

            sala = GetSalaFake();
            return sala;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    public Sala JsonToSala(String json)
    {
        Sala sala;
        try
        {
            JSONObject mainObject = new JSONObject(json);
            sala = new Sala();
            sala.setCapacidade(mainObject.getInt("capacidade"));
            sala.setIdSala(mainObject.getInt("idSala"));
            sala.setTipoDeSala(TipoSala.valueOf(mainObject.getString("tipoDeSala")));
            return sala;

        }
        catch (Exception e)
        {
            return null;
        }
    }

    private Sala GetSalaFake()
    {
        Sala sala = new Sala();
        sala.setIdSala(1);;
        sala.setCapacidade(50);
        sala.setTipoDeSala(TipoSala.LABORATORIO);
        return sala;
    }
}
