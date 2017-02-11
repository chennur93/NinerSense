package meghashyam.ninersense;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by megha on 11/16/2016.
 */

public class UpdateSec extends AsyncTask<String,Void,String> {
    String security;


    public getSecInterface activity = null;

    public UpdateSec(String security, getSecInterface activity) {
        this.security= security;
        this.activity = activity;
    }


    @Override
    protected String doInBackground(String... strings) {
        String data = null;
        try {
            data = URLEncoder.encode("secmode", "UTF-8")
                    + "=" + URLEncoder.encode(security, "UTF-8");


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }



        String text = "";
        BufferedReader reader=null;

        // Send data
        try
        {

            // Defined URL  where to send data
            URL url = new URL("http://192.168.1.4:80/UpdateSecurity.php");
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write( data );
            wr.flush();

            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;

            // Read Server Response
            while((line = reader.readLine()) != null)
            {
                // Append server response in string
                sb.append(line + "\n");
            }
            text = sb.toString();
        }

        catch(Exception ex)
        {
            Log.d("test1",ex.toString());

        }
        finally
        {
            try
            {

                reader.close();
            }

            catch(Exception ex) {}
        }

        return text;
    }

    public static interface getSecInterface{
        public void getResult(String resultpass);
    }

}
