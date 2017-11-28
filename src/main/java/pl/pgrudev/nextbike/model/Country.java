package pl.pgrudev.nextbike.model;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "country")
@XmlAccessorType(XmlAccessType.FIELD)
public class Country {

    @XmlElement(name = "city")
    private List<City> cityList;
    @XmlAttribute(name = "lat")
    private double lat;
    @XmlAttribute(name = "lng")
    private double lng;
    @XmlAttribute(name = "zoom")
    private int zoom;
    @XmlAttribute(name = "name")
    private String name;
    @XmlAttribute(name = "hotline")
    private String hotline;
    @XmlAttribute(name = "domain")
    private String domain;
    @XmlAttribute(name = "country")
    private String country;
    @XmlAttribute(name = "country_name")
    private String countryName;
    @XmlAttribute(name = "terms")
    private String terms;
    @XmlAttribute(name = "policy")
    private String policy;
    @XmlAttribute(name = "website")
    private String website;
    @XmlAttribute(name = "show_free_racks")
    private int showFreeRacks;


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

    public int getShowFreeRacks() {
        return showFreeRacks;
    }

    public void setShowFreeRacks(int showFreeRacks) {
        this.showFreeRacks = showFreeRacks;
    }
}
