package pl.pgrudev.core.api;

import pl.pgrudev.client.User;
import pl.pgrudev.core.api.annotations.AdminCommand;
import pl.pgrudev.core.api.annotations.LoginNotRequired;
import pl.pgrudev.core.api.annotations.PublicApi;
import pl.pgrudev.nextbike.model.Station;
import pl.pgrudev.nextbike.model.StationDictionary;
import pl.pgrudev.nextbike.model.Stats;

import java.util.List;

@PublicApi
public interface ClientApi {
    @LoginNotRequired
    String login(String login, String password);

    String logout(boolean disconnect);

    @LoginNotRequired
    default String ping(){
        return "pong";
    }

    Station getStation(int stationId);

    List<Station> getStations(List<Integer> stationsId);

    StationDictionary getStationsDictionary();

    String addFavouriteStation(int stationId);

    String removeFavouriteStation(int stationId);

    User getUserInfo();

    Stats teaserStats();

    @AdminCommand
    String registerNewUser();

    @LoginNotRequired
    public boolean isLogged();
}
