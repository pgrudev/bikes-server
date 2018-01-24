package pl.pgrudev.core.api;

import akka.actor.PoisonPill;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    private static final Logger logger = LogManager.getLogger(ClientApi.class);
    private static String STATUS_SUCCESS = "OK";
    private static String STATUS_FAILED = "Failure";
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
        //todo hashing password goes here
        if (user == null) {
            logger.debug("User not found: " + login);
            return STATUS_FAILED;
        }
        String userLogin = user.getLogin();
        if (userLogin != null) {
            if (passwordValid(login, password)) {
                actor.setLoggedIn(true);
                actor.setLogin(login);
                actor.setUser(user);
                logger.info("User: {} logged in, user details: ", userLogin, user);
            }
        }
        return STATUS_SUCCESS;
    }

    @Override
    public String logout(boolean disconnect) {
        logger.debug("Logging out user: {}", actor.getUser().getLogin());
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
        logger.info("Adding new favourite station: {} for user: {}", stationId, actor.getUser().getLogin());
        actor.getUser().addFavouriteStation(stationId);
        return STATUS_SUCCESS;
    }

    @Override
    public String removeFavouriteStation(int stationId) {
        logger.info("Removing favourite station (if exists): {} for user: {}", stationId, actor.getUser().getLogin());
        actor.getUser().removeFavouriteStation(stationId);
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
    public String registerNewUser(String firstName, String lastName, String login, String password, int userLevel) {
        if (isLoggedIn() && isAdmin()) {
            User newUser = new User(firstName, lastName, login, password, userLevel);
            logger.info("Registering new user: " + newUser);
            userRepository.save(new User(firstName, lastName, login, password, userLevel));
        }
        return STATUS_SUCCESS;
    }

    @Override
    public User checkUserInfo(String login) {
        User user = null;
        if (isLoggedIn() && isAdmin()) {
            user = userRepository.findByLogin(login);
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

    @Override
    public List<Station> getFavouriteStations() {
        List<Integer> favouriteStationsId = actor.getUser().getFavouriteStations();
        return favouriteStationsId.stream()
                .parallel()
                .map(id -> nextBikeApi.getStation(0, id))
                .collect(Collectors.toList());
    }

    private boolean isAdmin() {
        return userRepository.findByLogin(actor.getLogin()).getUserLevel() == USER_ADMIN_LEVEL;
    }

    private boolean passwordValid(String login, String password) {
        return userRepository.findByLogin(login).getPassword().equals(password);
    }

}
