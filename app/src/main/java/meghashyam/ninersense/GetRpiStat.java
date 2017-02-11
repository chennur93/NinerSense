package meghashyam.ninersense;

import android.os.AsyncTask;

/**
 * Created by megha on 12/7/2016.
 */

public class GetRpiStat extends AsyncTask<String, Void, String> {
    Boolean stat;
    GetRpiStat.getRpiStatInterface activity = null;
    String Status;


    public GetRpiStat(getRpiStatInterface activity){
        this.activity = activity;
        this.Status = Status;
    }

    @Override
    protected String doInBackground(String... params) {
        if(!Mapping.IfDatabaseConnectionAvailable()){
            stat = Boolean.FALSE;
            System.out.println("Raspberry Not Available");
        }
        else {
            stat = Boolean.TRUE;
            System.out.println("Connected to Raspberry ");
        }

        return Status = String.valueOf(stat);
    }

    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        activity.getResult(s);
    }



    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    public static interface getRpiStatInterface{
        public void getResult(String resultpass);
    }
}
