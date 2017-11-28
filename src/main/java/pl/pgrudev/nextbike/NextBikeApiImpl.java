package pl.pgrudev.nextbike;

import pl.pgrudev.nextbike.model.*;

import javax.inject.Named;

@Named
public class NextBikeApiImpl implements NextBikeApi {
    private Universe universe = Connector.downloadNewData();

    public NextBikeApiImpl(){}

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

   /* @Override //todo redundant? - getUniverse
    public Dictionary getStationDictionary() {
        return null; //kept in memory, loaded on startup, optional - reset by jmx bean
    }*/

    @Override
    public Stats getStats() {
        return null; //dunno yet
    }

    @Override
    public Universe getUniverse() {
        return universe;
    }
}
