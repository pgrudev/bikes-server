package pl.pgrudev.nextbike.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Station extends TransferClass {
    @SerializedName("bike_list")
    private List<Bike> bikes;
    @SerializedName("uid")
    private int stationId;
    @SerializedName("lat")
    private double lat;
    @SerializedName("lng")
    private double lng;
    @SerializedName("name")
    private String name;
    @SerializedName("spot")
    private boolean spot;
    @SerializedName("number")
    private int number;
    @SerializedName("bikes")
    private int bikesNo;
    @SerializedName("bike_racks")
    private int bikeRacks;
    @SerializedName("free_racks")
    private int freeRacks;
    @SerializedName("maintenance")
    private boolean maintenance;
    @SerializedName("terminal_type")
    private String terminalType;
    @SerializedName("bike_numbers")
    private transient String bikeNumbers;
    @SerializedName("bike_types")
    private transient Object bikeType;

    @SerializedName("rack_locks")
    private boolean rackLocks;

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

    public boolean getSpot() {
        return spot;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getBikesNo() {
        return bikesNo;
    }

    public void setBikesNo(int bikesNo) {
        this.bikesNo = bikesNo;
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
        return (String) bikeType;
    }

    public void setBikeType(String bikeType) {
        this.bikeType = bikeType;
    }

    public List<Bike> getBikes() {
        return bikes;
    }

    public void setBikes(List<Bike> bikes) {
        this.bikes = bikes;
    }

    public boolean isSpot() {
        return spot;
    }

    public void setSpot(boolean spot) {
        this.spot = spot;
    }

    public int getFreeRacks() {
        return freeRacks;
    }

    public void setFreeRacks(int freeRacks) {
        this.freeRacks = freeRacks;
    }

    public boolean isMaintenance() {
        return maintenance;
    }

    public void setMaintenance(boolean maintenance) {
        this.maintenance = maintenance;
    }

    public boolean isRackLocks() {
        return rackLocks;
    }

    public void setRackLocks(boolean rackLocks) {
        this.rackLocks = rackLocks;
    }
}
