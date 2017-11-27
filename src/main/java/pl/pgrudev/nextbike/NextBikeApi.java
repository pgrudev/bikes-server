package pl.pgrudev.nextbike;

import pl.pgrudev.nextbike.model.*;

interface NextBikeApi {
    Bike getBike(int bikeId);

    City getCity(int cityId);

    Country getCountry(int countryId);

    Station getStation(int cityId, int stationId);

    StationDictionary getStationDictionary();

    Stats getStats();

    Universe getUniverse();
}
