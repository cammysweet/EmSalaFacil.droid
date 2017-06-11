package emsalafacil.emsalafacildroid;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import emsalafacil.emsalafacildroid.Model.Curso;
import emsalafacil.emsalafacildroid.Model.Disciplina;
import emsalafacil.emsalafacildroid.Model.Ensalamento;
import emsalafacil.emsalafacildroid.Model.Sala;
import emsalafacil.emsalafacildroid.Model.TipoSala;
import emsalafacil.emsalafacildroid.Model.Turma;
import emsalafacil.emsalafacildroid.Model.Usuario;

/**
 * Created by camil on 08/04/2017.
 */

public class Util
{
    public static Turma JsonToTurma(String json)
    {
        Turma turma;

        try
        {
            turma = new Turma();

            JSONObject mainObject = new JSONObject(json);
            turma.setId(mainObject.getInt("Id"));
            turma.setDescricao(mainObject.getString("Descricao"));
            turma.setQuantidadeAlunos(mainObject.getInt("QuantidadeAlunos"));
            return  turma;
        }
        catch(Exception e)
        {
            return null;
        }
    }

    public static Sala JsonToSala(String json)
    {
        Sala sala;
        Gson gson = new Gson();
        try
        {
            JSONObject mainObject = new JSONObject(json);
            sala = new Sala();
            sala.setCapacidade(mainObject.getInt("Capacidade"));
            sala.setNome(mainObject.getString("Nome"));
            sala.setId(mainObject.getInt("Id"));
            sala.setTipoDeSala(gson.fromJson(mainObject.getString("TipoSala"),
                    TipoSala.class));
            return sala;

        }
        catch (Exception e)
        {
            return null;
        }
    }

    public static Ensalamento JsonToEnsalamento(String json)
    {
        Ensalamento ensalamento;

        try
        {
            ensalamento = new Ensalamento();
            JSONObject mainObject = new JSONObject(json);
            Gson gson = new Gson();

            ensalamento.setId(mainObject.getInt("Id"));
            ensalamento.setIdTurma(mainObject.getInt("IdTurma"));
            ensalamento.setDatainicio(Util.StringToDate(mainObject.getString("DataInicio")));
            ensalamento.setDataFim(Util.StringToDate(mainObject.getString("DataFim")));
            ensalamento.setDiaSemana(mainObject.getString("DiaSemana"));
            ensalamento.setDisponibilidadeProfessor(mainObject.getBoolean("DisponibilidadeProfessor"));
            ensalamento.setTurno(mainObject.getString("Turno"));
            ensalamento.setDisciplina( JsonToDisciplina(mainObject.getString("Disciplina")));
            ensalamento.setSala(JsonToSala(mainObject.getString("Sala")));
            ensalamento.setProfessor(JsonToUsuario(mainObject.getString("Professor")));

            return ensalamento;
        }
        catch (Exception e)
        {
            return  null;
        }
    }

    public static Disciplina JsonToDisciplina(String json)
    {
        try
        {
            Disciplina disciplina = new Disciplina();
            JSONObject mainObject = new JSONObject(json);
            disciplina.setId(mainObject.getInt("Id"));
            disciplina.setDescricao(mainObject.getString("Descricao"));
            return  disciplina;
        }
        catch (Exception e)
        {
            return  null;
        }
    }

    public static Curso JsonToCurso(String json)
    {
        Curso curso;

        try
        {
            curso = new Curso();
            JSONObject mainObject = new JSONObject(json);
            curso.setId(mainObject.getInt("Id"));
            curso.setNome(mainObject.getString("Nome"));
            return curso;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    public static Usuario JsonToUsuario(String json)
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
            aluno.setCurso(JsonToCurso(mainObject.getString("Curso")));
            aluno.setTurma(JsonToTurma(mainObject.getString("Turma")));

            return  aluno;
        }
        catch(Exception e)
        {
            return null;
        }
    }


    public static String webToString(InputStream inputStream) {
        InputStream localStream = inputStream;
        String localString = "";
        Writer writer = new StringWriter();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(localStream, "UTF-8"));
            String line = reader.readLine();
            while (line != null) {
                writer.write(line);
                line = reader.readLine();
            }
            localString = writer.toString();
            writer.close();
            reader.close();
            localStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return localString;
    }

    public static String rawToJson(InputStream inputStream)
    {
        InputStream localStream = inputStream;
        String jsonString = "";
        Writer writer = new StringWriter();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(localStream, "UTF-8"));
            String line = reader.readLine();
            while (line != null) {
                writer.write(line);
                line = reader.readLine();
            }
            jsonString = writer.toString();
            writer.close();
            reader.close();
            localStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonString;
    }

    public static String DateToString(Date data)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return sdf.format(data);
    }

    public static Date StringToDate(String data)
    {
        Date date;
        try {
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            date = formatter.parse(data);
        } catch (ParseException e) {
            return null;
        }
        return date;

    }

}
