package pl.pgrudev.nextbike.model.Bounds;

import com.google.gson.annotations.SerializedName;
import pl.pgrudev.nextbike.model.TransferClass;

public class Bound extends TransferClass {
    @SerializedName("north_east")
    private NorthEast northEast;
    @SerializedName("south_west")
    private SouthWest southWest;

    public NorthEast getNorthEast() {
        return northEast;
    }

    public void setNorthEast(NorthEast northEast) {
        this.northEast = northEast;
    }

    public SouthWest getSouthWest() {
        return southWest;
    }

    public void setSouthWest(SouthWest southWest) {
        this.southWest = southWest;
    }
}
