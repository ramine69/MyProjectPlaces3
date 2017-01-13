package loader.rami.com.myprojectplaces;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.content.Context.LOCATION_SERVICE;


public class FragmentSearch extends Fragment implements LocationListener {
    public FragmentSearch() {

        // Required empty public constructor
    }

    LocationManager locationManager;
    Location myLocation;
    EditText editText;
    private Database database;
    ListView listView;
    ArrayList<Places> allPlaces;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {




        View v = inflater.inflate(R.layout.fragment_fragment_serch, container, false);
        view = v;
        locationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
        // Inflate the layout for this fragment

            startGetLocation();
        IntentFilter filter = new IntentFilter("FINISHEEEEED");
        FinishedReciever reciever = new FinishedReciever();
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(reciever, filter);


        if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
           ;
        }

            Location lastKnown = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            //always check if null
            if (lastKnown != null) {
                lastKnown.getLongitude();

        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 1, this);

        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 1, this);


        final CheckBox checkBox = (CheckBox) v.findViewById(R.id.CbPlace);

        ListView listView = (ListView) v.findViewById(R.id.LVSerch);

        allPlaces = new ArrayList<>();

        Button b = (Button) v.findViewById(R.id.Btsearch);
        //EditText editText=(EditText)v.findViewById(R.id.EtSearch);


        final EditText editText = (EditText) v.findViewById(R.id.EtSearch);
        editText.getText().toString();

        Database db = new Database(getActivity());

        final ArrayList<Places> allPlaces = db.getAllPlaces();
        ArrayAdapter<Places> arrayAdapter = new ArrayAdapter<Places>(getActivity(), R.layout.simple_list_item_1,
                android.R.id.text1, allPlaces);

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


                if (editText != null) {

                    Database database = new Database(getActivity());
                    database.deletAllPlaces();
                }
                if(checkBox.isChecked()){

                    String data = editText.getText().toString();
                    
                    String l= myLocation.getLatitude()+","+myLocation.getLongitude();

                    Intent intent = new Intent(getActivity(), NearMeSearch.class);
                    intent.putExtra("search", data);
                    intent.putExtra("location", l);

                    getActivity().startService(intent);
                // MainActivity ac = (MainActivity) getActivity(this,FragmentMap.class);
                //ac.changeFr
                }else
                {
                    String data = editText.getText().toString();
                    Intent intent = new Intent(getActivity(), MyIntentService.class);
                    intent.putExtra("search", data);
                    getActivity().startService(intent);
                }

                // MainActivity ac = (MainActivity) getActivity(this,FragmentMap.class);
                //ac.changeFr


        }});
        return v;
    }

    public void startGetLocation() {


        if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location lastKnown = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        //always check if null
        if (lastKnown != null) {
            lastKnown.getLongitude();
        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 100, this);


        //  LocationManager.PASSIVE_PROVIDER - gets the location from other apps and serivces that currently use the location
        //LocationManager.NETWORK_PROVIDER- gets location from cell network or wifi
        // locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 1, this);
    }


    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override

    public void onProviderDisabled(String provider) {

    }

    class FinishedReciever extends BroadcastReceiver
    {

        @Override
        public void onReceive(Context context, Intent intent) {
            Database db = new Database(getActivity());

            final ArrayList<Places> allPlaces=db.getAllPlaces();
            ArrayAdapter<Places> arrayAdapter= new ArrayAdapter<Places>(getActivity(),  R.layout.simple_list_item_1,
                    android.R.id.text1, allPlaces  );

            listView=(ListView) view.findViewById(R.id.LVSerch);
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

            Toast.makeText(getActivity(), "Serviced is finished", Toast.LENGTH_SHORT).show();
        }
    }
}
