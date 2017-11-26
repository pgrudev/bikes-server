package pl.pgrudev.core.api;

import pl.pgrudev.client.User;
import pl.pgrudev.nextbike.model.Station;
import pl.pgrudev.nextbike.model.StationDictionary;
import pl.pgrudev.nextbike.model.Stats;

import javax.inject.Named;
import java.util.List;

@Named
public class ClientApiImpl implements ClientApi {
    @Override
    public String login(String login, String password) {
        return null;
    }

    @Override
    public String logout() {
        return null;
    }

    @Override
    public Station getStation(int stationId) {
        return null;
    }

    @Override
    public List<Station> getStations(List<Integer> stationsId) {
        return null;
    }

    @Override
    public StationDictionary getStationsDictionary() {
        return null;
    }

    @Override
    public String addFavouriteStation(int stationId) {
        return null;
    }

    @Override
    public String removeFavouriteStation(int stationId) {
        return null;
    }

    @Override
    public User getUserInfo() {
        return null;
    }

    @Override
    public Stats teaserStats() {
        return null;
    }

    @Override
    public boolean isLogged() {
        return false;
    }
}
