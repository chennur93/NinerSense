package meghashyam.ninersense;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ThermoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class ThermoFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    EditText con,cur,mode;
    Button click;
    String Heat="Heat";
    String Cold="Cold";
    String Off="Off";
    AsyncTask.Status status;
    ArrayList<UpdateThermo> updateThermo =new ArrayList<UpdateThermo>(2);
    UpdateThermo.Idata idata;
    int flag=0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_thermo, container, false);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        con = (EditText) getView().findViewById(R.id.con);
        cur = (EditText) getView().findViewById(R.id.curr);
        mode = (EditText) getView().findViewById(R.id.mode);
        click = (Button) getView().findViewById(R.id.button);
        con.setFocusable(true);
        cur.setFocusable(true);
        mode.setFocusable(true);
        final ThermoFragment context = ThermoFragment.this;


        click.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                flag=1;
                String con1 = con.getText().toString();
                String cur1 = cur.getText().toString();
                String mod = mode.getText().toString();

                con.setFocusable(false);
                cur.setFocusable(false);
                mode.setFocusable(false);
                int cur2 = Integer.valueOf((String) cur1);
                int con2 = Integer.valueOf((String) con1);
                UpdateThermo a=new UpdateThermo(ThermoFragment.this);
                //updateThermo.add(0,a);
                if (con2 == cur2) {
                    Toast.makeText(ThermoFragment.this.getActivity(),"UpdateThermo Off",Toast.LENGTH_SHORT).show();
                    flag=0;
                    con.setFocusableInTouchMode(true);
                    cur.setFocusableInTouchMode(true);
                    mode.setFocusableInTouchMode(true);
                } else if (con2 != cur2) {
                    if (((mod == Heat) && (cur2 > con2)) || (mod == Cold) && (cur2 < con2)){
                        Toast.makeText(ThermoFragment.this.getActivity(), "UpdateThermo Off", Toast.LENGTH_SHORT).show();}
                    else if(((mod == Heat) && (cur2 < con2)) || (mod == Cold) && (cur2 > con2))
                    {
                        Toast.makeText(ThermoFragment.this.getActivity(), "UpdateThermo On", Toast.LENGTH_SHORT).show();

/*((status == AsyncTask.Status.PENDING) || ((status == AsyncTask.Status.RUNNING) || (status == AsyncTask.Status.FINISHED))&&*/
                        if (!updateThermo.isEmpty()) {
                            status = updateThermo.get(0).getStatus();
                            updateThermo.get(0).cancel(true);
                            updateThermo.remove(0);}

                        UpdateThermo the1 = new UpdateThermo(ThermoFragment.this);
                        updateThermo.add(0, the1);
                        Integer[] am= new Integer[2];
                        am[0]=cur2;
                        am[1]=con2;
                        the1.execute(am);


                    }
                }
            }

        });

    }


    public void stopprogress() {
        Toast.makeText(ThermoFragment.this.getActivity(), "UpdateThermo Off", Toast.LENGTH_SHORT).show();
        // EditText a=(EditText)findViewById(R.id.mode);
        flag=0;
        con.setFocusableInTouchMode(true);
        cur.setFocusableInTouchMode(true);
        mode.setFocusableInTouchMode(true);
        mode.setText("Off");

    }


    public void setupdata(String a) {
        //EditText b=(EditText) findViewById(R.id.curr);
        //Toast.makeText(MainActivity.this, a, Toast.LENGTH_LONG).show();
        cur.setText(a);
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
