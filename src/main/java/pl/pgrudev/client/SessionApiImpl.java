package pl.pgrudev.client;

import pl.pgrudev.nextbike.model.Station;

import javax.inject.Named;
import java.util.List;

@Named
public class SessionApiImpl implements SessionApi {

    @Override
    public void addUser(String name, String surname, String login, String password, int accLevel) {

    }

    @Override
    public void deleteUser(int userId) {

    }

    @Override
    public void addFavouriteStation(int userId, Station station) {

    }

    @Override
    public void removeFavouriteStation(int userId, Station station) {

    }

    @Override
    public User getUserInfo(int userId) {
        return null;
    }

    @Override
    public List<Station> getFavouriteStations(int userId) {
        return null;
    }
}
