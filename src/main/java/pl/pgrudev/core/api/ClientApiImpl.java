package pl.pgrudev.core.api;

import akka.actor.PoisonPill;
import pl.pgrudev.client.User;
import pl.pgrudev.core.session.SessionActor;
import pl.pgrudev.nextbike.model.Station;
import pl.pgrudev.nextbike.model.StationDictionary;
import pl.pgrudev.nextbike.model.Stats;

import java.util.List;

public class ClientApiImpl implements ClientApi {
    private SessionActor actor;
    private static String STATUS_SUCCESS = "OK";
    public ClientApiImpl(SessionActor actor) {
        this.actor = actor;
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
    public StationDictionary getStationsDictionary() {
        return null;
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
