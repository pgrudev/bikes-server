package pl.pgrudev.nextbike.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "bike")
@XmlAccessorType(XmlAccessType.FIELD)
public class Bike extends TransferClass {
    @XmlAttribute(name = "number")
    private int bikeId;
    @XmlAttribute(name = "bike_type")
    private int type;
    @XmlAttribute(name = "active")
    private int active;
    @XmlAttribute(name = "state")
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

    public int isActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
