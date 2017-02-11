package meghashyam.ninersense;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class HomeFragment extends Fragment implements GetRpiStat.getRpiStatInterface {

    ImageView SecSysHome;
    ImageView GarageHome;
    ImageView VideoHome;
    TextView SecurityStatus;
    TextView GarageStatus;
    TextView TotalPower;
    TextView rpistat;
    Button rpiget;
    Button Livefeed;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        SecSysHome = (ImageView) getView().findViewById(R.id.securitysystemhome);
        GarageHome = (ImageView) getView().findViewById(R.id.garagehome);
        VideoHome = (ImageView) getView().findViewById(R.id.videohome);
        SecurityStatus = (TextView) getView().findViewById(R.id.securitystatus);
        GarageStatus = (TextView) getView().findViewById(R.id.garagestatus);
        TotalPower = (TextView) getView().findViewById(R.id.totalpower);
        rpistat = (TextView) getView().findViewById(R.id.rpistatus);
        rpiget = (Button) getView().findViewById(R.id.rpistatusget);
        Livefeed =(Button) getView().findViewById(R.id.LFeed);


        SecSysHome.setOnClickListener(new View.OnClickListener(){
            public int id = R.id.nav_security;
            public void onClick(View view) {
                Fragment fragment = new SecurityFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_frame, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }

        });

        rpiget.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                new GetRpiStat(HomeFragment.this).execute();
            }
        });

        Livefeed.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                Intent myIntent = new Intent(getActivity(), LayoutLF.class);
                getActivity().startActivity(myIntent);
            }
        });

        VideoHome.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {

                Intent myIntent = new Intent(getActivity(), IMsend.class);
                getActivity().startActivity(myIntent);

            }
        });


    }

    @Override
    public void getResult(String resultpass) {
        rpistat.setText(resultpass);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
