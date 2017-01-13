package loader.rami.com.myprojectplaces;


import android.app.FragmentTransaction;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {
    LocationManager locationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);




        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //show request permission message
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 907);
        } else {
            startGetLocation();
        }

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        FragmentSearch frtSerch = new FragmentSearch();
        ft.replace(R.id.fragCont,frtSerch, "fragmentSearch");
        ft.commit();
    }

    private void startGetLocation() {
    }

    public void changeFrags(Places currentPlace) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();

        //android.R.animator
        ft.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_out_right);

        FragmentMap fragmentMap = new FragmentMap();
        fragmentMap.lat=currentPlace.getLat();
        fragmentMap.lng= currentPlace.getLng();

        ft.replace(R.id.fragCont, fragmentMap, "detailFragment");
        ft.addToBackStack("replace");


        ft.commit();


    }


}
