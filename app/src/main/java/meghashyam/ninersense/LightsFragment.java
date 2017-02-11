package meghashyam.ninersense;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;


public class LightsFragment extends Fragment implements UpdateLights.getLightsInterface, UpdateLightsPi.getLightsInterface{

    View view;
    EditText edit_id,textView_floor;
    Button lights_update;
    SeekBar dimmer;
    public int i;

    public sendlight mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lights, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        edit_id=(EditText) getView().findViewById(R.id.editText_id);
        lights_update = (Button) getView().findViewById(R.id.lights_update);
        dimmer = (SeekBar) getActivity().findViewById(R.id.seekBar) ;



        lights_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i=Integer.parseInt(edit_id.getText().toString());
                //Log.d("Dimmer",dimmer.getProgress() +i+ "");
                new UpdateLights(i, dimmer.getProgress(),LightsFragment.this).execute();
                new UpdateLightsPi(i,dimmer.getProgress(),LightsFragment.this).execute();

                MainActivity.Intense = dimmer.getProgress();
            }
        });
    }

    @Override
    public void getResult(String resultpass) {

    }

    public interface sendlight{
        public void lightgaya(int i);
    }
}
