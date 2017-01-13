package loader.rami.com.myprojectplaces;

import android.app.IntentService;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.R.attr.key;

/**
 * Created by jbt on 1/13/2017.
 */

public class NearMeSearch extends IntentService {

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public NearMeSearch(String name) {
        super(name);
    }

    public NearMeSearch() {
        super("");
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        String searchWord=intent.getStringExtra("search");
        String Mylocation=intent.getStringExtra("l");
        LocationManager MyLocation = (LocationManager) getSystemService(LOCATION_SERVICE);


        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://maps.googleapis.com/maps/api/place/nearbysearch/json?MyLocation+searchWord+&key=AIzaSyA9tE4wl3-W2SYBM9zw7sbUQfTt6xvEHw0")
                .build();

        Response response = null;
        String allJSON="";

        try {
            try {
                response = client.newCall(request).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                allJSON =  response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }


            JSONObject newObject = new JSONObject(allJSON);
            JSONArray array =  newObject.getJSONArray("results");

            for (int i = 0; i < array.length() ; i++) {
                JSONObject obj = array.getJSONObject(i);
                String name = obj.getString("name");
                JSONObject geo = obj.getJSONObject("geometry");
                JSONObject loc = geo.getJSONObject("location");
                String lat= loc.getString("lat");
                String lng=loc.getString("lng");

                Places places = new Places(name,i,lat,lng);
                Database db= new Database(this);
                db.AddPlaces(places);

                Log.d( "hfh","json" );


                // broadcastMessage.putExtra()



            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Intent broadcastMessage = new Intent("FINISHEEEEED");
        LocalBroadcastManager.getInstance(this).sendBroadcast( broadcastMessage);

        //intent

    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */


}

