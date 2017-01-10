package loader.rami.com.myprojectplaces;

import android.app.IntentService;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gms.location.places.Place;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class MyIntentService extends IntentService {


    public MyIntentService() {
        super("MyIntentService");
    }



    @Override
    protected void onHandleIntent(Intent intent) {



        String searchWord=intent.getStringExtra("search");



        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://maps.googleapis.com/maps/api/place/textsearch/json?query="+searchWord+"&key=AIzaSyA9tE4wl3-W2SYBM9zw7sbUQfTt6xvEHw0")
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

                Intent broadcastMessage = new Intent("FINISHEEEEED");
                // broadcastMessage.putExtra()

                LocalBroadcastManager.getInstance(this).sendBroadcast( broadcastMessage);



            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        //intent

    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */

}
