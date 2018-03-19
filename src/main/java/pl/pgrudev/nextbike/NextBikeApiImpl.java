package pl.pgrudev.nextbike;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.pgrudev.nextbike.model.*;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Component
public class NextBikeApiImpl implements NextBikeApi {

    @Value("${dictionary.url:https://nextbike.net/maps/nextbike-official.json}")
    private String source;
    private Connector connector = new Connector(source);

    private Universe universe = connector.downloadNewData(Connector.Type.UNIVERSE, "");

    @Override
    public Bike getBike(int bikeId) {
        return null;
    }

    @Override
    public City getCity(int cityId) {
        return connector.downloadNewData(Connector.Type.CITY, String.valueOf(cityId));
    }

    @Override
    public List<Country> getCountry(String domain) {
        Universe universeCountry = connector.downloadNewData(Connector.Type.COUNTRY, domain);
        if (universeCountry != null) {
            return universeCountry.getCountries();
        }
        return new ArrayList<>();
    }

    @Override
    public Station getStation(int cityId, int stationId) {
        return connector.downloadNewData(Connector.Type.STATION, String.valueOf(stationId));
    }

    @Override
    public Stats getStats() {
        return null; //dunno yet
    }

    @Override
    public Universe getUniverse() {
        return universe;
    }
}
