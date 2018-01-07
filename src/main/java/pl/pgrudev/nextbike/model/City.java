package pl.pgrudev.nextbike.model;

import com.google.gson.annotations.SerializedName;
import pl.pgrudev.nextbike.model.Bounds.Bound;

import java.util.List;

public class City extends TransferClass {

    @SerializedName("places")
    private List<Station> stations;
    @SerializedName("uid")
    private int cityId;
    @SerializedName("lat")
    private double lat;
    @SerializedName("lng")
    private double lng;
    @SerializedName("zoom")
    private int zoom;
    @SerializedName("maps_icon")
    private String mapsIcon;
    @SerializedName("alias")
    private String alias;
    @SerializedName("break")
    private boolean available;
    @SerializedName("name")
    private String name;
    @SerializedName("num_places")
    private int numPlaces;
    @SerializedName("refresh_rate")
    private int refreshRate;
    @SerializedName("bounds")
    private Bound bounds;


    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public List<Station> getStations() {
        return stations;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public int getZoom() {
        return zoom;
    }

    public void setZoom(int zoom) {
        this.zoom = zoom;
    }

    public String getMapsIcon() {
        return mapsIcon;
    }

    public void setMapsIcon(String mapsIcon) {
        this.mapsIcon = mapsIcon;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public boolean getAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumPlaces() {
        return numPlaces;
    }

    public void setNumPlaces(int numPlaces) {
        this.numPlaces = numPlaces;
    }

    public int getRefreshRate() {
        return refreshRate;
    }

    public void setRefreshRate(int refreshRate) {
        this.refreshRate = refreshRate;
    }

    public Bound getBounds() {
        return bounds;
    }

    public void setBounds(Bound bounds) {
        this.bounds = bounds;
    }
}
