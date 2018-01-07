package pl.pgrudev.nextbike.model;

import com.google.gson.annotations.SerializedName;

public class Bike extends TransferClass {
    @SerializedName("number")
    private int bikeId;
    @SerializedName("bike_type")
    private int type;
    @SerializedName("active")
    private boolean active;
    @SerializedName("state")
    private String state;

    public int getBikeId() {
        return bikeId;
    }

    public void setBikeId(int bikeId) {
        this.bikeId = bikeId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
