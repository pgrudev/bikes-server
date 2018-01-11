package pl.pgrudev.core.api;

import akka.actor.PoisonPill;
import pl.pgrudev.client.User;
import pl.pgrudev.core.api.annotations.AdminCommand;
import pl.pgrudev.core.session.SessionActor;
import pl.pgrudev.nextbike.NextBikeApiImpl;
import pl.pgrudev.nextbike.model.*;
import pl.pgrudev.repository.UserRepository;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ClientApiImpl implements ClientApi {
    private static String STATUS_SUCCESS = "OK";
    private static String STATUS_FAILED = "failure";
    private static int USER_ADMIN_LEVEL = 1;
    private final NextBikeApiImpl nextBikeApi;
    private SessionActor actor;
    private Dictionary dictionary;
    private UserRepository userRepository;

    public ClientApiImpl(SessionActor actor, NextBikeApiImpl nextBikeApi, Dictionary dictionary, UserRepository userRepository) {
        this.actor = actor;
        this.nextBikeApi = nextBikeApi;
        this.dictionary = dictionary;
        this.userRepository = userRepository;
    }

    @Override
    public String login(String login, String password) {
        User user = userRepository.findByLogin(login);
        if(user == null ){
            return STATUS_FAILED;
        }
        String userLogin = user.getLogin();
        if (userLogin != null) {
            if (passwordValid(login, password)) {
                actor.setLoggedIn(true);
                actor.setLogin(login);
                actor.setUser(user);
            }
        }
        return STATUS_SUCCESS;
    }

    private boolean passwordValid(String login, String password) {
        return userRepository.findByLogin(login).getPassword().equals(password);
    }

    @Override
    public String logout(boolean disconnect) {
        actor.setLoggedIn(false);
        actor.setUser(null);
        actor.setLogin(null);
        if (disconnect) actor.self().tell(PoisonPill.getInstance(), actor.sender());
        return STATUS_SUCCESS;
    }

    @Override
    public Station getStation(Integer cityId, Integer stationId) {
        return nextBikeApi.getStation(cityId, stationId);
    }

    @Override
    public List<Station> getStations(Integer cityId, List<Integer> stationsId) {
        return stationsId.stream()
                .map(stationId -> this.getStation(cityId, stationId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Station> getAllStationsForCity(int cityId) {
        return nextBikeApi.getCity(cityId).getStations();
    }

    @Override
    public List<Bike> getAllBikesForStation(int cityId, int stationId) {
        return nextBikeApi.getStation(cityId, stationId).getBikes();
    }

    @Override
    public List<Country> getCountry(String domain) {
        return nextBikeApi.getCountry(domain);
    }

    @Override
    public Dictionary getDictionary() {
        return dictionary;
    }

    @Override
    public String addFavouriteStation(int stationId) {
        return STATUS_SUCCESS;
    }

    @Override
    public String removeFavouriteStation(int stationId) {
        return STATUS_SUCCESS;
    }

    @Override
    public User getUserInfo() {
        if (isLoggedIn()) {
            return actor.getUser();
        }
        return null;
    }

    @Override
    public Stats teaserStats() {
        return null;
    }

    @Override
    public String registerNewUser(String firstName, String lastName, String login, int userLevel) {
        if (isLoggedIn() && isAdmin()) {
            userRepository.save(new User(firstName, lastName, login, userLevel));
        }
        return STATUS_SUCCESS;
    }

    @Override
    public User checkUserInfo(String login) {
        User user = new User();
        if (isLoggedIn() && isAdmin()) {
            user = actor.getUser();
        }
        return user;
    }


    @Override
    public boolean isLoggedIn() {
        return actor.isLoggedIn();
    }

    @Override
    public List<String> getCommands() {
        Predicate<Method> nonAdminCommands = m -> Arrays.stream(m.getAnnotations())
                .noneMatch(annotation -> AdminCommand.class.isAssignableFrom(annotation.annotationType()));
        return Arrays.stream(this.getClass().getInterfaces())
                .flatMap(i -> Arrays.stream(i.getDeclaredMethods()))
                .filter(nonAdminCommands)
                .filter(method -> !method.isSynthetic())
                .map(Method::getName)
                .collect(Collectors.toList());
    }

    private boolean isAdmin() {
        return userRepository.findByLogin(actor.getLogin()).getUserLevel() == USER_ADMIN_LEVEL;
    }
}
