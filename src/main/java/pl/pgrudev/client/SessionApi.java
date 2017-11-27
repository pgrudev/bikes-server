package pl.pgrudev.client;

import pl.pgrudev.nextbike.model.Station;

import java.util.List;

public interface SessionApi {
    void addUser(String name, String surname, String login, String password, int accLevel);

    void deleteUser(int userId);

    void addFavouriteStation(int userId, Station station);

    void removeFavouriteStation(int userId, Station station);

    User getUserInfo(int userId);

    List<Station> getFavouriteStations(int userId);

}
