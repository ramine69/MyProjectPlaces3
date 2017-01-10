package loader.rami.com.myprojectplaces;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Movie;

import java.util.ArrayList;

import static android.R.attr.version;

/**
 * Created by RAMI.N on 01/01/2017.
 */

public class Database extends SQLiteOpenHelper{
    private static final int VER = 1;
    private android.database.sqlite.SQLiteDatabase db;

    private  SQLiteDatabase database;


    public Database(Context context) {
        super(context , "PlacesDataBase", null, VER);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        
        db.execSQL("CREATE TABLE Places (id INTEGER PRIMARY KEY AUTOINCREMENT , name TEXT  , lat TEXT, lng TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int newVersion, int oldVersion) {
        db.execSQL("DROP TABLE IF EXSIST Places ");
        onCreate(db);

    }
    public  void  open(){
        database = getWritableDatabase();

    }
    public  void  close(){
        super.close();
    }



    public void updatePlaces(Places Places){
        ContentValues contentValues = new ContentValues();
        open();

        String sql = String.format(" INSERT INTO Places(name,lat,lng) VALUES ('%s',%f,%f)",Places.getName(),Places.getLat(),Places.getLng() );
        open();
        database.execSQL(sql);
        close();
    }
    public void AddPlaces (Places places){
        open();
        ContentValues contentValues = new ContentValues();
     //   contentValues.put("id", places.getId());
        contentValues.put("name", places.getName());
        contentValues.put("lat", places.getLat());
        contentValues.put("lng", places.getLng());

        database.insert("Places", null, contentValues);





        }
    public  void  deletAllPlaces ( ){
        open();
        database.delete("Places",null, null);

    }
    public  void  deletePlaces ( Places places){
        open();
        database.execSQL(String.format("DELETE FROM Places WHERE id=%d", "_id" ,places.getId()));

        close();
    }
    public ArrayList<Places> getAllPlaces(){
        ArrayList<Places> places = new ArrayList<>();
        open();
        Cursor cursor = database.query("Places",null,null,null,null,null,null);
        while (cursor.moveToNext()){
            int idIndex = cursor.getColumnIndex("id");
            int nameIndex = cursor.getColumnIndex("name");
            int latIndex = cursor.getColumnIndex("lat");
            int lngIndex = cursor.getColumnIndex("lng");
            int id=cursor.getInt(idIndex);
            String name = cursor.getString(nameIndex);
            String lat = cursor.getString(latIndex);
            String lng = cursor.getString(lngIndex);

            Places place= new Places(name, id, lat,lng);
            places.add(place);
                    }
        cursor.close();
        close();
        return places;

    }
}








