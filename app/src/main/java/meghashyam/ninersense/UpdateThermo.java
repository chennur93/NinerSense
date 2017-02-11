package meghashyam.ninersense;

import android.os.AsyncTask;

/**
 * Created by megha on 12/6/2016.
 */

public class UpdateThermo extends AsyncTask<Integer,Integer,Integer> {
    private Idata idata;
    ThermoFragment conte;
    int a;

    public UpdateThermo(ThermoFragment conte) {
        this.conte = conte;
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
        conte.stopprogress();
    }

    @Override
    protected Integer doInBackground(Integer... params) {
        int cur=params[0];
        int con=params[1];
        //Integer.parseInt(params[1].toString());
        if(cur<con){

            for (int i = 1; i <=con-cur ; i++) {
                a=cur+i;
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // Escape early if cancel() is called
                //  if (isCancelled()) break;
                publishProgress(a);
                //return a;
            }
        }
        else {

            for (int i = 1; i <=cur-con ; i++) {
                a=cur-i;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // Escape early if cancel() is called
                // if (isCancelled()) break;
                publishProgress(a);
                //return a;
            }
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        // super.onProgressUpdate(values);
        conte.setupdata(values[0].toString());
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    public interface Idata{
        void setupdata(String a);
        void stopprogress();
    }
}
