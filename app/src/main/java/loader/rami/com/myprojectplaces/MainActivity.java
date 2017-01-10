package loader.rami.com.myprojectplaces;


import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        FragmentTransaction ft = getFragmentManager().beginTransaction();
        FragmentSearch frtSerch = new FragmentSearch();
        ft.replace(R.id.fragCont,frtSerch, "fragmentSearch");
        ft.commit();
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
