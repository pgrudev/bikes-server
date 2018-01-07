package pl.pgrudev.nextbike.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Country extends TransferClass {
    @SerializedName("cities")
    private List<City> cityList;
    @SerializedName("lat")
    private double lat;
    @SerializedName("lng")
    private double lng;
    @SerializedName("zoom")
    private int zoom;
    @SerializedName("name")
    private String name;
    @SerializedName("hotline")
    private String hotline;
    @SerializedName("domain")
    private String domain;
    @SerializedName("country")
    private String country;
    @SerializedName("country_name")
    private String countryName;
    @SerializedName("terms")
    private String terms;
    @SerializedName("policy")
    private String policy;
    @SerializedName("website")
    private String website;
    @SerializedName("show_free_racks")
    private boolean showFreeRacks;


    public List<City> getCityList() {
        return cityList;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHotline() {
        return hotline;
    }

    public void setHotline(String hotline) {
        this.hotline = hotline;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public String getPolicy() {
        return policy;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public boolean getShowFreeRacks() {
        return showFreeRacks;
    }

    public void setShowFreeRacks(boolean showFreeRacks) {
        this.showFreeRacks = showFreeRacks;
    }
}
