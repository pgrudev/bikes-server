package pl.pgrudev.nextbike.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "place")
@XmlAccessorType(XmlAccessType.FIELD)
public class Station {

    @XmlAttribute(name = "uid")
    private int stationId;
    @XmlAttribute(name = "lat")
    private double lat;
    @XmlAttribute(name = "lng")
    private double lng;
    @XmlAttribute(name = "name")
    private String name;
    @XmlAttribute(name = "spot")
    private int spot;
    @XmlAttribute(name = "number")
    private int number;
    @XmlAttribute(name = "bikes")
    private int bikes;
    @XmlAttribute(name = "bike_racks")
    private int bikeRacks;
    @XmlAttribute(name = "terminal_type")
    private String terminalType;
    @XmlAttribute(name = "bike_numbers")
    private String bikeNumbers;
    @XmlAttribute(name = "bike_types")
    private String bikeType;

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSpot() {
        return spot;
    }

    public void setSpot(int spot) {
        this.spot = spot;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getBikes() {
        return bikes;
    }

    public void setBikes(int bikes) {
        this.bikes = bikes;
    }

    public int getBikeRacks() {
        return bikeRacks;
    }

    public void setBikeRacks(int bikeRacks) {
        this.bikeRacks = bikeRacks;
    }

    public String getTerminalType() {
        return terminalType;
    }

    public void setTerminalType(String terminalType) {
        this.terminalType = terminalType;
    }

    public String getBikeNumbers() {
        return bikeNumbers;
    }

    public void setBikeNumbers(String bikeNumbers) {
        this.bikeNumbers = bikeNumbers;
    }

    public String getBikeType() {
        return bikeType;
    }

    public void setBikeType(String bikeType) {
        this.bikeType = bikeType;
    }
}
