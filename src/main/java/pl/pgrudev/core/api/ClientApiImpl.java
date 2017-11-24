package pl.pgrudev.core.api;

import pl.pgrudev.core.session.Response;

import javax.inject.Named;
import java.util.List;

@Named
public class ClientApiImpl implements ClientApi {
    @Override
    public Response login(String login, String password) {
        return null;
    }

    @Override
    public Response logout() {
        return null;
    }

    @Override
    public String ping() {
        return "pong";
    }


    @Override
    public Response getStation(int stationId) {
        return null;
    }

    @Override
    public Response getStations(List<Integer> stationsId) {
        return null;
    }

    @Override
    public Response getStationsDictionary() {
        return null;
    }

    @Override
    public Response addFavouriteStation(int stationId) {
        return null;
    }

    @Override
    public Response removeFavouriteStation(int stationId) {
        return null;
    }

    @Override
    public Response getUserInfo() {
        return null;
    }

    @Override
    public Response teaserStats() {
        return null;
    }

    @Override
    public boolean isLogged() {
        return false;
    }
}
