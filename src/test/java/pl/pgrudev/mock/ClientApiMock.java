package pl.pgrudev.mock;

import akka.actor.Actor;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.pgrudev.client.User;
import pl.pgrudev.core.api.ClientApi;
import pl.pgrudev.core.netty.WebSocketSessionActor;
import pl.pgrudev.core.session.SessionActor;
import pl.pgrudev.nextbike.NextBikeApiImpl;
import pl.pgrudev.nextbike.model.*;
import pl.pgrudev.repository.UserRepository;

import javax.inject.Inject;
import java.util.List;

public class ClientApiMock implements ClientApi {

    private static String STATUS_SUCCESS = "OK";
    private static String STATUS_FAILED = "Failure";
    private static int USER_ADMIN_LEVEL = 1;
    private final NextBikeApiImpl nextBikeApi;
    private SessionActor actor;
    private Dictionary dictionary;
    private UserRepository userRepository;

    @Inject
    private PasswordEncoder passwordEncoder;

    public ClientApiMock(SessionActor actor, NextBikeApiImpl nextBikeApi, Dictionary dictionary, UserRepository userRepository) {
        this.actor = actor;
        this.nextBikeApi = nextBikeApi;
        this.dictionary = dictionary;
        this.userRepository = userRepository;
    }

    @Override
    public String login(String login, String password) {
        actor.setLogin(login);
        actor.setLoggedIn(true);
        return STATUS_SUCCESS;
    }

    @Override
    public String logout(boolean disconnect) {
        return null;
    }

    @Override
    public Station getStation(Integer cityId, Integer stationId) {
        return null;
    }

    @Override
    public List<Station> getStations(Integer cityId, List<Integer> stationsId) {
        return null;
    }

    @Override
    public List<Station> getAllStationsForCity(int cityId) {
        return null;
    }

    @Override
    public List<Bike> getAllBikesForStation(int cityId, int stationId) {
        return null;
    }

    @Override
    public List<Country> getCountry(String domain) {
        return null;
    }

    @Override
    public Dictionary getDictionary() {
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
    public String registerNewUser(String firstName, String lastName, String login, String password, int userLevel) {
        return null;
    }

    @Override
    public User checkUserInfo(String login) {
        return null;
    }

    @Override
    public boolean isLoggedIn() {
        return false;
    }

    @Override
    public List<String> getCommands() {
        return null;
    }

    @Override
    public List<Station> getFavouriteStations() {
        return null;
    }
}
