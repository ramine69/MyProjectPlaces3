package loader.rami.com.myprojectplaces;

import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.maps.MapFragment;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentMap.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentMap#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentMap extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


    String lat="32";
    String lng="34";

    public FragmentMap() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View v = inflater.inflate(R.layout.fragment_fragment_map, container, false);


        MapFragment fragment= new MapFragment();
        FragmentTransaction transaction= getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragCont, fragment).commit();


       // Button b = (Button) v.findViewById(R.id.Btserch);
       // b.setOnClickListener(new View.OnClickListener() {
        /*    @Override
            public void onClick(View v) {
                MainActivity ac = (MainActivity) getActivity();
                ac.changeFrags();
            }
        });*/
        return v;

    }
}