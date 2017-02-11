package meghashyam.ninersense;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by megha on 11/13/2016.
 */

public class GetPass extends AsyncTask<String, Void, String> {
    String uname;
    String password;

    getPassInterface activity = null;
    
    public GetPass(String uname, String password, getPassInterface activity) {
        this.activity = activity;
        this.uname = uname;
        this.password = password;
    }

    @Override
    protected String doInBackground(String... strings) {

        String data = null;
        try {
            data = URLEncoder.encode("username", "UTF-8")
                    + "=" + URLEncoder.encode(uname, "UTF-8");

            data += "&" + URLEncoder.encode("password", "UTF-8") + "="
                    + URLEncoder.encode(password, "UTF-8");


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }



        String text = "";
        BufferedReader reader=null;

        // Send data
        try
        {
            // Defined URL  where to send data
            URL url = new URL("http://192.168.1.3:80/login.php");
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            assert data != null;
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



    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        activity.getResult(s);
    }



    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    public static interface getPassInterface{
        public void getResult(String resultpass);
    }

}
