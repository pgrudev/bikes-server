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

    String logout(boolean disconnect);

    @LoginNotRequired
    default String ping() {
        return "pong";
    }

    Station getStation(Integer cityId, Integer stationId);

    List<Station> getStations(Integer cityId, List<Integer> stationsId);

    List<Station> getAllStationsForCity(int cityId);

    List<Bike> getAllBikesForStation(int cityId, int stationId);

    List<Country> getCountry(String domain);

    Dictionary getDictionary();

    String addFavouriteStation(int stationId);

    String removeFavouriteStation(int stationId);

    User getUserInfo();

    Stats teaserStats();

    @AdminCommand
    String registerNewUser(String firstName, String lastName, String login, String password, int userLevel);

    @AdminCommand
    User checkUserInfo(String login);

    @LoginNotRequired
    boolean isLoggedIn();

    @LoginNotRequired
    List<String> getCommands();

    List<Station> getFavouriteStations();
}
