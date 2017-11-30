package pl.pgrudev.nextbike;

import pl.pgrudev.nextbike.model.*;

interface NextBikeApi {
    Bike getBike(int bikeId);

    City getCity(int cityId);

    Country getCountry(String domain);

    Station getStation(int cityId, int stationId);

    Stats getStats();

    Universe getUniverse();
}
