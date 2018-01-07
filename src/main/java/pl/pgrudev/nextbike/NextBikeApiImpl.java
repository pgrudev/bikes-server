package pl.pgrudev.nextbike;

import pl.pgrudev.nextbike.model.*;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
public class NextBikeApiImpl implements NextBikeApi {
    private Universe universe = Connector.downloadNewData(Connector.Type.UNIVERSE, "");

    //public NextBikeApiImpl(){}

    @Override
    public Bike getBike(int bikeId) {
        return null;
    }

    @Override
    public City getCity(int cityId) {
        return Connector.downloadNewData(Connector.Type.CITY, String.valueOf(cityId));
    }

    @Override
    public List<Country> getCountry(String domain) {
        Universe universeCountry = Connector.downloadNewData(Connector.Type.COUNTRY, domain);
        if (universeCountry != null) {
            return universeCountry.getCountries();
        }
        return new ArrayList<>();
    }

    @Override
    public Station getStation(int cityId, int stationId) {
        return Connector.downloadNewData(Connector.Type.STATION, String.valueOf(stationId));
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
