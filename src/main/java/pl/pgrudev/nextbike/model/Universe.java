package pl.pgrudev.nextbike.model;

import java.util.List;

public class Universe extends TransferClass {

    private List<Country> countries;

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }
}
