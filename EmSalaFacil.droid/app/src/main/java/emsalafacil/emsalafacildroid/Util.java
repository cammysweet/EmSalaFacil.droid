package emsalafacil.emsalafacildroid;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by camil on 08/04/2017.
 */

public class Util
{
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
