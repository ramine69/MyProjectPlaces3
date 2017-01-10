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
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;



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



        MapFragment mapFragment= new MapFragment();
        FragmentTransaction transaction= getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragCont, mapFragment).commit();
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {

                //String locationText;
                //String[] latlngarr= locationText.split(",");

                //double dLatitude=Double.parseDouble(latlngarr[0]);
                //double dLongitude=Double.parseDouble(latlngarr[1]);
                double dLatitude=Double.parseDouble(lat);
                double dLongitude=Double.parseDouble(lng);


                googleMap.addMarker(new MarkerOptions().position(new LatLng(dLatitude, dLongitude))
                        .title("My parking spot").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));

                googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(dLatitude, dLongitude),15));


            }

        });

       /*Button b = (Button) v.findViewById(R.id.Btserch);
       b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               MainActivity ac = (MainActivity) getActivity();ac.changeFrags();
            }
        })*/;
        return v;

    }
}