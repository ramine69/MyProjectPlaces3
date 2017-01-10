package loader.rami.com.myprojectplaces;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;



public class FragmentSearch extends Fragment {
    public FragmentSearch() {
        // Required empty public constructor
    }
    EditText editText;
    private  Database database;
    ListView listView;
    ArrayList<Places> allPlaces;
    View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        IntentFilter filter = new IntentFilter("FINISHEEEEED");

        FinishedReciever reciever = new FinishedReciever();

        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(reciever , filter);


        View v = inflater.inflate(R.layout.fragment_fragment_serch, container, false);
        view=v;
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
                if(editText!=null){
                Database database = new Database(getActivity());
                database.deletAllPlaces();}

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
    class FinishedReciever extends BroadcastReceiver
    {

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(getActivity(), "Serviced is finished", Toast.LENGTH_SHORT).show();
        }
    }
}
