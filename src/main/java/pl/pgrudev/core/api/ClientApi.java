package pl.pgrudev.core.api;

import pl.pgrudev.client.User;
import pl.pgrudev.core.api.annotations.AdminCommand;
import pl.pgrudev.core.api.annotations.LoginNotRequired;
import pl.pgrudev.core.api.annotations.PublicApi;
import pl.pgrudev.nextbike.model.*;

import java.util.List;

@PublicApi
public interface ClientApi {
    @LoginNotRequired
    String login(String login, String password);

    @LoginNotRequired //todo remove
    String logout(boolean disconnect);

    @LoginNotRequired
    default String ping(){
        return "pong";
    }

    @LoginNotRequired //todo remove
    Station getStation(Integer cityId, Integer stationId);
    @LoginNotRequired //todo remove
    List<Station> getStations(Integer cityId, List<Integer> stationsId);
    @LoginNotRequired //todo remove
    List<Station> getAllStationsForCity(int cityId);
    @LoginNotRequired //todo remove
    List<Bike> getAllBikesForStation(int cityId, int stationId);
    @LoginNotRequired //todo remove
    List<Country> getCountry(String domain);
    @LoginNotRequired //todo remove
    Dictionary getDictionary();

    String addFavouriteStation(int stationId);

    String removeFavouriteStation(int stationId);

    User getUserInfo();

    Stats teaserStats();

    @AdminCommand
    String registerNewUser(String firstName, String lastName,String login, int userLevel);

    @AdminCommand
    User checkUserInfo(String login);

    @LoginNotRequired
     boolean isLoggedIn();

    @LoginNotRequired
    List<String> getCommands();
}
