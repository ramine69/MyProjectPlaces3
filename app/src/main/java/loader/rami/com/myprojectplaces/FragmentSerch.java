package loader.rami.com.myprojectplaces;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentSerch.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentSerch#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentSerch extends Fragment {
    public FragmentSerch() {
        // Required empty public constructor
    }
    EditText editText;
    private  Database database;
    ArrayList<Places> allPlaces;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View v = inflater.inflate(R.layout.fragment_fragment_serch, container, false);
        ListView listView = (ListView)  v.findViewById(R.id.LVSerch);

        allPlaces= new ArrayList<>();


        Button b = (Button) v.findViewById(R.id.Btsearch);
        //EditText editText=(EditText)v.findViewById(R.id.EtSearch);
        final EditText editText =(EditText) v.findViewById(R.id.EtSearch);
        editText.getText().toString();

        Database db = new Database(getActivity());

       final ArrayList<Places> allPlaces=db.getAllPlaces();
        ArrayAdapter<Places> arrayAdapter= new ArrayAdapter<Places>(getActivity(),  R.layout.simple_list_item_1,
                android.R.id.text1, allPlaces  );

        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {


                Places currentPlace = allPlaces.get(position);
                MainActivity activity = (MainActivity) getActivity();
                activity.changeFrags(currentPlace);


                Log.d("ass", "sas");
            }
        });




        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data=editText.getText().toString();
                Intent intent = new Intent(getActivity(),MyIntentService.class);
             intent.putExtra("search", data);

               getActivity().startService(intent);

               // MainActivity ac = (MainActivity) getActivity(this,FragmentMap.class);
                //ac.changeFr
            }

        });
        return v;
    }
}
