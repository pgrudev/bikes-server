package pl.pgrudev.nextbike;

import pl.pgrudev.nextbike.model.*;

import javax.inject.Named;

@Named
public class NextBikeApiImpl implements NextBikeApi {
    @Override
    public Bike getBike(int bikeId) {
        return null;
    }

    @Override
    public City getCity(int cityId) {
        return null;
    }

    @Override
    public Country getCountry(int countryId) {
        return null;
    }

    @Override
    public Station getStation(int cityId, int stationId) {
        return null;
    }

    @Override
    public StationDictionary getStationDictionary() {
        return null;
    }

    @Override
    public Stats getStats() {
        return null;
    }

    @Override
    public Universe getUniverse() {
        return null;
    }
}
