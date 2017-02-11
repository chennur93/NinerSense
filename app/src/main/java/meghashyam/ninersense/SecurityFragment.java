package meghashyam.ninersense;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;


public class SecurityFragment extends Fragment implements UpdateSec.getSecInterface, AdapterView.OnItemSelectedListener {

    Spinner SecMode;
    String pos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_security, container, false);
    }



    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        SecMode = (Spinner) getView().findViewById(R.id.securitySpinner);
        SecMode.setOnItemSelectedListener(this);

        List<String> SecurityMode = new ArrayList<String>();
        SecurityMode.add("Armed-Stay");
        SecurityMode.add("Armed-Away");
        SecurityMode.add("Disarm");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, SecurityMode);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        SecMode.setAdapter(dataAdapter);

    }

            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                            pos = parent.getItemAtPosition(position).toString();
                        new UpdateSec(pos,SecurityFragment.this).execute();
                        new UpdateSecPi(pos,SecurityFragment.this).execute();
                        break;
                    case 1:
                            System.out.println("Connected to Raspberry ");
                            pos = parent.getItemAtPosition(position).toString();
                        new UpdateSecPi(pos,SecurityFragment.this).execute();
                        new UpdateSec(pos,SecurityFragment.this).execute();
                        break;
                    case 2:
                            System.out.println("Connected to Raspberry ");
                            pos = parent.getItemAtPosition(position).toString();
                        new UpdateSecPi(pos,SecurityFragment.this).execute();
                        new UpdateSec(pos,SecurityFragment.this).execute();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


    @Override
    public void getResult(String resultpass) {

    }
}
