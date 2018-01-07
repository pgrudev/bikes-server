package pl.pgrudev.nextbike;

import pl.pgrudev.nextbike.model.*;

import java.util.List;

interface NextBikeApi {
    Bike getBike(int bikeId);

    City getCity(int cityId);

    List<Country> getCountry(String domain);

    Station getStation(int cityId, int stationId);

    Stats getStats();

    Universe getUniverse();
}
