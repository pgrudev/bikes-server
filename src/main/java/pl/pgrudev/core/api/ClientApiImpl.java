package pl.pgrudev.core.api;

import akka.actor.PoisonPill;
import pl.pgrudev.client.User;
import pl.pgrudev.core.api.annotations.AdminCommand;
import pl.pgrudev.core.session.SessionActor;
import pl.pgrudev.nextbike.NextBikeApiImpl;
import pl.pgrudev.nextbike.model.*;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ClientApiImpl implements ClientApi {
    private static String STATUS_SUCCESS = "OK";
    private final NextBikeApiImpl nextBikeApi;
    private SessionActor actor;
    private Dictionary dictionary;

    public ClientApiImpl(SessionActor actor, NextBikeApiImpl nextBikeApi, Dictionary dictionary) {
        this.actor = actor;
        this.nextBikeApi = nextBikeApi;
        this.dictionary = dictionary;
    }

    @Override
    public String login(String login, String password) {
        return STATUS_SUCCESS;
    }

    @Override
    public String logout(boolean disconnect) {
        actor.setLoggedIn(false);
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
        return null;
    }

    @Override
    public Stats teaserStats() {
        return null;
    }

    @Override
    public String registerNewUser() {
        return STATUS_SUCCESS;
    }

    @Override
    public boolean isLogged() {
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
}
