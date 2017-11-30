package pl.pgrudev.nextbike.model;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "city")
@XmlAccessorType(XmlAccessType.FIELD)
public class City extends TransferClass {

    @XmlElement(name = "place")
    private List<Station> stations;
    @XmlAttribute(name = "uid")
    private int cityId;
    @XmlAttribute(name = "lat")
    private double lat;
    @XmlAttribute(name = "lng")
    private double lng;
    @XmlAttribute(name = "zoom")
    private int zoom;
    @XmlAttribute(name = "maps_icon")
    private String mapsIcon;
    @XmlAttribute(name = "alias")
    private String alias;
    @XmlAttribute(name = "break")
    private int available;
    @XmlAttribute(name = "name")
    private String name;
    @XmlAttribute(name = "num_places")
    private int numPlaces;
    @XmlAttribute(name = "refresh_rate")
    private int refreshRate;
    @XmlAttribute
    private String bounds;


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

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
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

    public String getBounds() {
        return bounds;
    }

    public void setBounds(String bounds) {
        this.bounds = bounds;
    }
}
