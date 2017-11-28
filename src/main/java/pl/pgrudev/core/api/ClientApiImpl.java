package pl.pgrudev.core.api;

import akka.actor.PoisonPill;
import pl.pgrudev.client.User;
import pl.pgrudev.core.session.SessionActor;
import pl.pgrudev.nextbike.NextBikeApiImpl;
import pl.pgrudev.nextbike.model.Station;
import pl.pgrudev.nextbike.model.Dictionary;
import pl.pgrudev.nextbike.model.Stats;

import java.util.List;

public class ClientApiImpl implements ClientApi {
    private final NextBikeApiImpl nextBikeApi;
    private SessionActor actor;
    private static String STATUS_SUCCESS = "OK";
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
        if(disconnect) actor.self().tell(PoisonPill.getInstance(),actor.sender());
        return STATUS_SUCCESS;
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
}
