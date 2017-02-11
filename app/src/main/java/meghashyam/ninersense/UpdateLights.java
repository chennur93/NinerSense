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
 * Created by megha on 11/18/2016.
 */

public class UpdateLights extends AsyncTask<String,Void,String> {
    public int id,seekbar_result,floor;

    public getLightsInterface activity = null;

    public UpdateLights(int id, int seekbar_result, LightsFragment activity) {
        this.id = id;
        this.seekbar_result = seekbar_result;
        this.activity = activity;
    }

    @Override
    protected String doInBackground(String... strings) {
        Log.d("demo",id +","+seekbar_result+"");

        String data = null;
        try {
            data = URLEncoder.encode("lightID", "UTF-8")
                    + "=" + URLEncoder.encode(String.valueOf(id), "UTF-8");

            data += "&" + URLEncoder.encode("floor", "UTF-8") + "="
                    + URLEncoder.encode(String.valueOf(0), "UTF-8");

            data += "&" + URLEncoder.encode("lightstatus", "UTF-8") + "="
                    + URLEncoder.encode(String.valueOf(seekbar_result), "UTF-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }



        String text = "";
        BufferedReader reader=null;

        // Send data
        try
        {

            // Defined URL  where to send data
            URL url = new URL("http://192.168.1.4:80/lightsUpdate.php");
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
            Log.d("ServerMSG", text);
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


    public static interface getLightsInterface{
        public void getResult(String resultpass);
    }

}
