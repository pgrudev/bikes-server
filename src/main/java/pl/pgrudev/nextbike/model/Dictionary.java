package pl.pgrudev.nextbike.model;

import pl.pgrudev.nextbike.NextBikeApiImpl;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.HashMap;

@Named
public class Dictionary {

    private HashMap<String, HashMap<Integer, HashMap<Integer, Station>>> dictionary = new HashMap<>();

    @Inject
    private NextBikeApiImpl nextBikeApi;
    private Universe universe;

    public Dictionary() {
    }

    @PostConstruct
    public void init() {
        this.universe = nextBikeApi.getUniverse();
        generateDictionary();
    }

    private void generateDictionary() {
        universe.getCountries().forEach(country -> {
            HashMap<Integer, HashMap<Integer, Station>> citiesDictionary = new HashMap<>();
            country.getCityList().forEach(city -> {
                HashMap<Integer, Station> stationsDictionary = new HashMap<>();
                city.getStations().forEach(station -> {
                    stationsDictionary.put(station.getStationId(), station);
                });
                citiesDictionary.put(city.getCityId(), stationsDictionary);
            });
            dictionary.put(country.getName(), citiesDictionary);
        });
    }

    private void refreshDictionary() {
        this.universe = null;
        this.universe = nextBikeApi.getUniverse();
        generateDictionary();
    }

    @Override
    public String toString() {
        return "Dictionary{" +
                "dictionary=" + dictionary +
                '}';
    }
}
