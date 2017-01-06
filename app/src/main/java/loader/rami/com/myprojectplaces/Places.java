package loader.rami.com.myprojectplaces;

/**
 * Created by jbt on 12/27/2016.
 */

public class Places {

   private int id ;
    private String name ;
    private String lat;
    private String lng;

    public Places(String name, int id, String lat, String lng) {
        setId(id);
        setName(name);
        setLat(lat);
        setLng(lng);
    }
    public Places(String name,  String lat, String lng) {
        setName(name);
        setLat(lat);
        setLng(lng);

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return  " " + name ;
    }
}
