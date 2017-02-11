package meghashyam.ninersense;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PowerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class PowerFragment extends Fragment {

    TextView powerval;
    Button UpdateP;
    int Lwatt = 10;
    int power=0;

    private OnFragmentInteractionListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_power, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        powerval = (TextView) getView().findViewById(R.id.Tv_power);
        UpdateP = (Button) getActivity().findViewById(R.id.update_power);

 //       power = (MainActivity.Intense)*Lwatt;
  //      MainActivity.Tpower += power;
  //      powerval.setText(String.valueOf(MainActivity.Tpower));

        UpdateP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                power = (MainActivity.Intense)*Lwatt;
                MainActivity.Tpower += power;
                powerval.setText(String.valueOf(MainActivity.Tpower));
            }
        });


    }



    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
